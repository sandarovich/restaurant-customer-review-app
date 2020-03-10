(ns user
  (:require [restaurant-customer-review-app.main.core
             :refer [start-app stop-app migrate-database] :as rcv]
            [clojure.tools.namespace.repl :as repl]
            [clojure.tools.logging :refer :all])
  (:gen-class))

(defn reset []
  (stop-app)
  (repl/refresh :after 'rcv/start-app))


(defn -main [& args]
  ;(rcv/migrate-database)
  (rcv/start-app)
  (info "[OK] Restaurant customer review application has been successfully started..."))