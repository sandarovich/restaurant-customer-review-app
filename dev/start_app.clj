(ns start-app
  (:require [restaurant-customer-review-app.main.core
             :refer [start-app stop-app migrate-database] :as rcv]
            [clojure.tools.namespace.repl :as repl]
            [restaurant-customer-review-app.main.db.repository :as repo]
            [clojure.pprint :as pprint]
            [clojure.string :as string]
            [hikari-cp.core :refer :all]
            [restaurant-customer-review-app.main.database :as db]
            [restaurant-customer-review-app.main.config :as config]
            [clojure.java.jdbc :as jdbc]))

(defn reset []
  (stop-app)
  (repl/refresh :after 'rcv/start-app))

;; save some typing
(def pp pprint/pprint)
(defn ppl [x]
  "pretty print w/ extra trailing line"
  (pp x) (println))

(defn ppr
  "pretty print w/ trailing result indicator ;;=>"
  [x]
  (print (string/replace (with-out-str (pp x)) #"\n$" ""))
  (println "  ;;=>"))

(defn ppsv
  "Pretty print an sqlvec"
  [sv]
  (println
    (string/join ""
                 ["[\""
                  (-> (first sv)
                      (string/replace #"\"" "\\\\\"")
                      (string/replace #"\n" "\n  "))
                  "\""
                  (when (seq (rest sv)) "\n,")
                  (string/replace
                    (string/join ","
                                 (map #(with-out-str (pp %)) (rest sv)))
                    #"\n$"
                    "")
                  "]\n"])))

(defmacro ex
  "Example macro: Pretty print code,
   eval, then pretty print result"
  [& code]
  `(do
     (ppr (quote ~@code))
     (ppl ~@code)))

(defmacro exsv
  "Example macro for sqlvec: Pretty print code,
   eval, then pretty print sqlvec"
  [& code]
  `(do
     (ppr (quote ~@code))
     (ppsv ~@code)))


(defn test_queries []
  (exsv (repo/retrieve_health_score-sqlvec))
  (ex (repo/retrieve_health_score db/dbspec {}))
  (exsv (repo/find_all_occurrencies_in_review-sqlvec {:searched "Test"}))
  (ex (repo/find_all_occurrencies_in_review db/dbspec {:searched "Test"}))
  (exsv (repo/insert-customer-review-sqlvec{:review_text "Test" :score 2 :customer_id 3}))
  (ex (repo/insert-customer-review db/dbspec {:review_text "Test" :score 2 :customer_id 3})))


(defn -main [& args]
  (println "Restaurant customer review application has been started...")
  (rcv/migrate-database)
  (rcv/start-app)
  (test_queries))