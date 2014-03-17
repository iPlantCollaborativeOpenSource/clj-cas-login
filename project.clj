(defproject clj-cas-login "0.1.0-SNAPSHOT"
  :description "Used to obtain service tickets from CAS."
  :url "https://github.com/iPlantCollaborativeOpenSource/clj-cas-login"
  :license {:name "BSD"
            :url "http://www.iplantcollaborative.org/sites/default/files/iPLANT-LICENSE.txt"}
  :scm {:connection "scm:git:git@github.com:iPlantCollaborativeOpenSource/clj-cas-login.git"
        :developerConnection "scm:git:git@github.com:iPlantCollaborativeOpenSource/clj-cas-login.git"
        :url "git@github.com:iPlantCollaborativeOpenSource/clj-cas-login.git"}
  :pom-addition [:developers
                 [:developer
                  [:url "https://github.com/orgs/iPlantCollaborativeOpenSource/teams/iplant-devs"]]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.cemerick/url "0.1.1"]
                 [clj-http "0.9.1"]
                 [enlive "1.1.5"]]
  :repositories [["sonatype-nexus-snapshots"
                  {:url "https://oss.sonatype.org/content/repositories/snapshots"}]]
  :deploy-repositories [["sonatype-nexus-staging"
                         {:url "https://oss.sonatype.org/service/local/staging/deploy/maven2/"}]])
