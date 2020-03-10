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
                 [prismatic/schema "1.1.9"]
                 [http-kit "2.3.0"]
                 [ring "1.8.0"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.0"]
                 [compojure "1.6.1"]

                 ; Database
                 [com.layerware/hugsql "0.5.1"]
                 [org.postgresql/postgresql "42.2.10"]
                 [hikari-cp "2.10.0"]
                 [org.flywaydb/flyway-core "5.2.4"]]
  :main user
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.3.1"]]}})
