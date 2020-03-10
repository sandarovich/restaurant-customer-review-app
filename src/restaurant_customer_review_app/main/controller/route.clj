(ns restaurant-customer-review-app.main.controller.route
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :refer :all]
            [ring.util.response :refer :all]
            [restaurant-customer-review-app.main.db.repository :as repo]
            [restaurant-customer-review-app.main.db.database :as db]
            [mount.core :as mount :refer [defstate]]
            [clojure.tools.logging :refer :all])
  (:gen-class))

(defn add-feedback-handler [req]
  (let [review_text (get-in req [:body "review_text"])
        score (get-in req [:body "score"])
        customer_id (get-in req [:body "customer_id"])]
    (repo/insert-customer-review db/dbspec {:review_text review_text
                                            :score score
                                            :customer_id customer_id}))
  (status 201))

(defn health-indicator-handler [req]
  (response (repo/retrieve_health_score db/dbspec {})))

(defn occurrences-handler [req]
  (response (repo/find_all_occurrencies_in_review db/dbspec {:searched (str (get (:params req) :word))})))

(defroutes app-routes
           (POST "/feedback" [] add-feedback-handler)
           (GET "/occurrences" [] occurrences-handler)
           (GET "/health" [] health-indicator-handler)
           (route/not-found "Error, page was not found!"))

(def app
  (-> app-routes
      (wrap-json-body)
      (wrap-json-response)
      ; Temporarly disable to be able to run POST. Improve https://github.com/ring-clojure/ring-anti-forgery
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))

(defn args->sever-config [args]
  {:port  (Integer/parseInt
            (or (first args)
                (System/getenv "PORT")
                "3000"))
   :join? false})

(defn start-server [server-config]
  (when-let [server (server/run-server app server-config)]
    (info "Web server has started!")
    server))

(defstate http-server :start (start-server (args->sever-config
                                             (mount/args)))
          :stop (http-server :timeout 100))

(comment
  (mount/start)
  (mount/stop))


