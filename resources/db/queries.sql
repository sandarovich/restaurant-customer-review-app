-- A ":result" value of ":1" specifies a single record
-- :name find_all_occurrencies_in_review :? :1
-- :doc Get all occurencies by provided text
select * from find_all_occurrencies_in_review(:searched);

-- A ":result" value of ":1" specifies a single record
-- :name retrieve_health_score :? :1
-- :doc Get  health score
select health_score from health;

-- A :result value of :n below will return affected row count:
-- :name insert-customer-review :! :n
-- :doc Insert a CUSTOMER_REVIEW
insert into customer_review(review_text, score, customer_id) values (:review_text, :score, :customer_id);