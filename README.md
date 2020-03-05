# Restaurant-customer-review-app

[![Build Status](https://travis-ci.org/sandarovich/restaurant-customer-review-app.svg?branch=master)](https://travis-ci.org/sandarovich/restaurant-customer-review-app)

Application for restaurant owners, that wants to gather reviews and feedback from the customers. 
This helps to build a database of reviews which further can be analysed to unlock insights (popular dishes, customer satisfaction, etc.)


## Installation

Download from https://github.com/sandarovich/restaurant-customer-review-app

## Usage
### Prerequisites
1. Install latest Docker an Docker-compose on your environment to be able to run Postgress docker image.


    $ docker-compose -f docker/docker-compose.yml up
    
    
   wait for it to initialize completely, and visit http://localhost:8080 
   user: postgres,  password: postgres
    
2. Please install on your environment build tool[Leiningen](https://leiningen.org/#install)


    $ lein with-profile dev run


## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Troubleshooting

1. In case of docker-compose issue, please execute on linux environment:


    $  export DOCKER_HOST=127.0.0.1
    
    sudo netstat -ntlp | grep LISTEN



### That You Think
### Might be Useful

## License

Copyright © 2020 Sandarovich

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
