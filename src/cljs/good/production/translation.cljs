(ns good.production.translation
  (:require-macros
    [taoensso.tower :as tower :refer (dict-compile)])
  (:require
    [good.production.state :as state]
    [taoensso.tower :as tower]))

(def ^:private tconfig
  {:fallback-locale :en
   :compiled-dictionary (tower/dict-compile "ui-translation.edn")})

(def t (tower/make-t tconfig))

(defn label [label-name]
  (t @state/locale label-name))