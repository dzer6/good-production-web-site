(defproject good-production-web-site "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure            "1.8.0"]
                 [http-kit                       "2.2.0"]
                 [compojure                      "1.5.1"]
                 [ring                           "1.5.0"]
                 [org.clojure/tools.logging      "0.3.1"]
                 [org.slf4j/slf4j-api            "1.7.21"]
                 [org.slf4j/log4j-over-slf4j     "1.7.21"]
                 [ch.qos.logback/logback-classic "1.1.7"]
                 [org.clojure/clojurescript      "1.9.225"]
                 [com.taoensso/tower             "3.1.0-beta5" :exclusions [com.taoensso/encore]]
                 [com.taoensso/encore            "2.79.1"]
                 [figwheel                       "0.5.6"]
                 [reagent                        "0.5.1"]]

  :main ^:skip-aot good.production.core

  :source-paths ["src/clj"]
  :test-paths   ["test/clj"]
  :target-path   "target/%s"

  :resources-path "resources"

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-figwheel "0.5.6"]
            [lein-bower "0.5.1"]
            [lein-ancient "0.6.10"]]

  :bower-dependencies [[react "15.2.1"]
                       [bootstrap "3.3.7"]]

  :bower {:directory "resources/public/lib"}

  :figwheel {:http-server-root "public" ;; this will be in resources/
             :css-dirs ["resources/public/css"]}

  :cljsbuild {:builds
              {:main {:source-paths ["src/cljs" "src/cljs-dev"]
                      :compiler {:output-to "resources/public/app/main.js"
                                 :output-dir "resources/public/app/main"
                                 :optimizations :none
                                 :source-map true}}
               :test {:source-paths ["src/cljs" "test/cljs" ]
                      :compiler {:pretty-print true}}}}

  :profiles {:uberjar {:aot :all}
             :prod {:cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                                               :compiler {:output-to "resources/public/js/app.js"
                                                          :main "good.production.core"
                                                          :optimizations :advanced
                                                          :pretty-print false}}}}}})
