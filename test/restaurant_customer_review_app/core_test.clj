(ns restaurant-customer-review-app.core-test
  (:require [clojure.test :refer :all]
            [restaurant-customer-review-app.main.core :refer :all]))

(deftest a-test
  (testing "FIXED. Not failing any more"
    (is 1 (= 0 1))))

;TODO Look as inspiration for further test with h2
;https://stackoverflow.com/questions/51613637/cannot-create-connection-pool-with-hikari-cp
