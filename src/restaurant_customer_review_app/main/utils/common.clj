(ns restaurant-customer-review-app.main.utils.common
  (:require [clojure.string :as str]))

(def non-blank? (complement str/blank?))

(defn min-length? [length text]
  (>= (count text) length))

(defn non-blank-with-min-length? [length text]
  (and (non-blank? text) (min-length? length text)))

(defn valid-score? [score] (<= 1 5 score))

(defn valid-customer? [customer] (<= 1 100 customer))       ; Let's assume that we have only 100 customers

(defn valid-review? [text] (non-blank-with-min-length? 50 text))

;TODO Add  Swagger/Integration test/Validators in
;(def swagger-config
;  {:ui "/swagger"
;   :spec "/swagger.json"
;   :options {:ui {:validatorUrl nil}
;             :data {:info {:version "1.0.0", :title "Restaurant Customer review Application"}}}})

;(s/defschema AddCustomerReviewSchema
;  {:review_text (s/constrained s/Str util/valid-review?)
;   :score       (s/constrained s/Int util/valid-score?)
;   :customer_id (s/constrained s/Int util/valid-customer?)})

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
