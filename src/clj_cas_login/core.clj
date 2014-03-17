(ns clj-cas-login.core
  (:use [clj-http.cookies :only [cookie-store]])
  (:require [cemerick.url :as curl]
            [clj-http.client :as http]
            [clojure.string :as string]
            [net.cgrand.enlive-html :as html]))

(defn- get-login-page
  [cookies cas-url service-url]
  (-> (http/get cas-url
                {:query-params {:service service-url}
                 :as           :stream
                 :cookie-store cookies})
      :body
      (html/html-resource)))

(defn- get-initial-form
  [login-page]
  (->> (html/select login-page [:form :input])
       (map :attrs)
       (filter #(= "hidden" (:type %)))
       (map (juxt :name :value))
       (into {})))

(defn- get-form
  [cookies user pass cas-url service-url]
  (let [login-page (get-login-page cookies cas-url service-url)]
    {:action (-> (html/select login-page [:form]) first :attrs :action)
     :params (assoc (get-initial-form login-page)
               :username user
               :password pass)}))

(defn- build-submit-url
  [action cas-url service-url]
  (-> (curl/url cas-url (string/replace action #";.*" ""))
      (assoc :query {:service service-url})
      str))

(defn- submit-form
  [{:keys [action params]} cookies cas-url service-url]
  (-> (http/post (build-submit-url action cas-url service-url)
                 {:form-params      params
                  :cookie-store     cookies
                  :follow-redirects false})))

(defn get-ticket
  [user pass cas-url service-url]
  (let [cookies (cookie-store)]
    (-> (get-form cookies user pass cas-url service-url)
        (submit-form cookies cas-url service-url)
        (get-in [:headers "Location"])
        (curl/url)
        (get-in [:query "ticket"]))))
