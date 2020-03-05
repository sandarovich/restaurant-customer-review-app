(ns start-app
  (:require [restaurant-customer-review-app.main.core
             :refer [start-app stop-app migrate-database] :as rcv]
            [clojure.tools.namespace.repl :as repl]))

(defn reset []
  (stop-app)
  (repl/refresh :after 'rcv/start-app))

(defn -main [& args]
  (println "Restaurant customer review application has been started...")
  (rcv/migrate-database)
  (rcv/start-app))