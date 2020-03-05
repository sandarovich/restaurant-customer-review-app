create domain STAR_SCORE as smallint check (value between 1 and 5);

CREATE TABLE IF NOT EXISTS CUSTOMER_REVIEW(
  id BIGSERIAL PRIMARY KEY,
  review_text TEXT,
  score STAR_SCORE NOT NULL,
  customer_id bigint NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT NOW()
);

/* Some sample data */
insert into CUSTOMER_REVIEW(review_text,score,customer_id) VALUES ('Excellent dishes', 5, 1);
insert into CUSTOMER_REVIEW(review_text,score,customer_id) VALUES ('Not for me!', 2, 1);
insert into CUSTOMER_REVIEW(review_text,score,customer_id) VALUES ('Awfull service', 1, 2);
insert into CUSTOMER_REVIEW(review_text,score,customer_id) VALUES ('Better to avoid', 3, 3);

CREATE VIEW health AS
  SELECT ROUND(AVG(score)::numeric, 1) FROM CUSTOMER_REVIEW;