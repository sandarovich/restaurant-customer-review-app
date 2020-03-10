(ns restaurant-customer-review-app.main.core
  (:require [restaurant-customer-review-app.main.config :as config]
            [restaurant-customer-review-app.main.db.database :as db]
            [restaurant-customer-review-app.main.controller.route :as route]
            [mount.core :as mount]))


(defn start-app []
  (mount/start #'config/root #'db/datasource #'route/http-server))


(defn migrate-database []
  (mount/start #'config/root #'db/datasource)
  (db/migrate)
  (mount/stop #'db/datasource))

(defn stop-app []
  (mount/stop))