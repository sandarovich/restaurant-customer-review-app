(ns restaurant-customer-review-app.main.database
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

(comment
  (mount/start)
  (migrate)
  (mount/stop))
