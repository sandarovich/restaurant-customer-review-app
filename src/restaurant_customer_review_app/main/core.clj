(ns restaurant-customer-review-app.main.core
  (:require [restaurant-customer-review-app.main.config :as config]
            [restaurant-customer-review-app.main.database :as db]
            [mount.core :as mount]))

(defn start-app []
  (mount/start))


(defn migrate-database []
  (mount/start #'config/root #'db/datasource)
  (db/migrate)
  (mount/stop #'db/datasource))

(defn stop-app []
  (mount/stop))