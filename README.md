# Restaurant-customer-review-app

[![Build Status](https://travis-ci.org/sandarovich/restaurant-customer-review-app.svg?branch=master)](https://travis-ci.org/sandarovich/restaurant-customer-review-app)

Application for restaurant owners, that wants to gather reviews and feedback from the customers. 
This helps to build a database of reviews which further can be analysed to unlock insights (popular dishes, customer satisfaction, etc.)


## Installation
```bash
    git clone https://github.com/sandarovich/restaurant-customer-review-app
```
## Usage
### Prerequisites
1. Install latest Docker an Docker-compose on your environment to be able to run Postgress docker image.
```bash
 docker-compose -f docker/docker-compose.yml up
 ```
    
   wait for it to initialize completely, and visit http://localhost:8080 for UI Db tool 
   user: postgres,  password: postgres
    
2. Please install on your environment build tool [Leiningen](https://leiningen.org/#install)
3. Run
```bash
    lein with-profile dev run
```
## Examples

With curl (assuming your app runs on port 3000):

1. POST /feedback  (submit customer review with payload which contains review text, 5 stars score and identifier of the customer)

```bash
curl -X POST \
  http://127.0.0.1:3000/feedback \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"review_text": "Test",
	"score" : 2,
	"customer_id": 3
}'
```
Response:
``` status 201```

2. GET /occurrences?word= (retrieve the number of occurrences for specific word, which helps to understand the popularity)
```bash
curl -X GET \
  'http://127.0.0.1:3000/occurrences?word=Excellent' \
  -H 'cache-control: no-cache' \'
```
Response:
```
{
    "find_all_occurrencies_in_review": 1
}
```
3. GET /health (return the overall business health of the restaurant using average score by reviews)

```bash
curl -X GET \
  http://127.0.0.1:3000/health \
  -H 'cache-control: no-cache' \
```
Response:
```
{
    "health_score": 2.6
}
```   

## License

Copyright © 2020 Sandarovich

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
