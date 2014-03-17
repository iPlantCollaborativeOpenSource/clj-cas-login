# clj-cas-login

A Clojure library designed to obtain service tickets from a CAS server.

## Usage

```clojure
(use '[clj-cas-login])

(get-ticket username password cas-url service-url)
```
