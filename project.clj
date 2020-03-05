(defproject restaurant-customer-review-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]

                 ;Loggging
                 [org.clojure/tools.logging "0.3.1"]
                 [ch.qos.logback/logback-classic "1.1.3"]

                 ;Configs
                 [aero "1.1.3"]
                 [mount "0.1.16"]

                 ; Web
                 [ring "1.8.0"]
                 [compojure "1.6.1"]

                 ; Database
                 [com.layerware/hugsql "0.5.1"]
                 [org.postgresql/postgresql "42.2.10"]
                 [hikari-cp "2.8.0"]
                 [org.flywaydb/flyway-core "5.2.4"]]

  ;:main ^:skip-aot restaurant-customer-review-app.core
  :main start-app
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.3.1"]]}})
