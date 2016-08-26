(ns good.production.handler
  (:require
    [good.production.state :as state]
    [cljs.core.async :refer [<!]]))

(defn set-locale [locale]
  (reset! state/locale locale))