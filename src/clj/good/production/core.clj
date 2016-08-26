(ns good.production.core
  (:gen-class)
  (:require
    [org.httpkit.server    :as http-kit-server]
    [compojure.core        :refer (defroutes GET POST)]
    [compojure.route       :as route]
    [compojure.handler     :as handler]
    [ring.util.response    :as rur]))

(defroutes my-routes
  (GET  "/dev"  _ (rur/file-response "resources/public/html/dev.html"))
  (GET  "/prod" _ (rur/file-response "resources/public/html/prod.html"))
  ;;
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))

(def my-ring-handler
  (-> my-routes
      handler/site))

(defn -main [& args]
  (http-kit-server/run-server (var my-ring-handler) {:port 8080}))