(ns restaurant-customer-review-app.main.db.database
  (:require [restaurant-customer-review-app.main.config :as config]
            [mount.core :as mount]
            [hikari-cp.core :as hikari])
  (:import [org.flywaydb.core Flyway]))

(defn- make-datasource []
  (hikari/make-datasource (config/database)))

(mount/defstate datasource :start (make-datasource) :stop (hikari/close-datasource datasource))

(defn migrate []
  (.. (Flyway/configure)
      (dataSource datasource)
      (locations (into-array String ["classpath:db/migration"]))
      (baselineOnMigrate true)
      load
      migrate))

;; TODO: Ask Clojure Guru. This is not correct as we already
;; have config for flyway migration and hikari set-up,
;; in spite of what the HugSQL and Hikari docs seem to say
;; without this jdbc is not set-up properly.
(def dbspec
  {:dbtype "postgresql"
   :classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :dbname "postgres" ;; db name
   :subname "//localhost:5432/postgres"
   :user "postgres"
   :password "postgres"
   :port 5432
   :hostname "localhost"})

(comment
  (mount/start)
  (migrate)
  (mount/stop))
