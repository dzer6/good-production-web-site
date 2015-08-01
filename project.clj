(defproject good-production-web-site "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure            "1.7.0"]
                 [http-kit                       "2.1.19"]
                 [compojure                      "1.4.0"]
                 [ring                           "1.4.0"]
                 [org.clojure/tools.logging      "0.3.1"]
                 [org.slf4j/slf4j-api            "1.7.12"]
                 [org.slf4j/log4j-over-slf4j     "1.7.12"]
                 [ch.qos.logback/logback-classic "1.1.3"]
                 [org.clojure/clojurescript      "1.7.28"]
                 [cljs-http                      "0.1.35"]
                 [com.taoensso/tower             "3.0.2"]
                 [figwheel                       "0.3.7"]
                 [reagent                        "0.5.0"]]

  :main ^:skip-aot good.production.core

  :source-paths ["src/clj"]
  :test-paths   ["test/clj"]
  :target-path   "target/%s"

  :resources-path "resources"

  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-figwheel "0.3.3"]
            [lein-bower "0.5.1"]
            [lein-ancient "0.6.7"]]

  :bower-dependencies [[react "0.13.3"]
                       [bootstrap "3.3.4"]]

  :bower {:directory "resources/public/lib"}

  :figwheel {:http-server-root "public" ;; this will be in resources/
             :port 3449                 ;; default
             :css-dirs ["resources/public/css"] }

  :cljsbuild {:builds
              {:main {:source-paths ["src/cljs" "src/cljs-dev"]
                      :compiler {:output-to "resources/public/app/main.js"
                                 :output-dir "resources/public/app/main"
                                 :optimizations :none
                                 :warnings true
                                 :source-map true}}
               :test {:source-paths ["src/cljs" "test/cljs" ]
                      :compiler {:pretty-print true}}}}

  :profiles {:uberjar {:aot :all}
             :prod {:cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                                               :compiler {:output-to "resources/public/js/app.js"
                                                          :main "good.production.core"
                                                          :optimizations :advanced
                                                          :pretty-print false}}}}}})
