(ns restaurant-customer-review-app.main.db.repository
  (:require [hugsql.core :as hugsql]))

(hugsql/def-sqlvec-fns "db/queries.sql")
(hugsql/def-db-fns "db/queries.sql")