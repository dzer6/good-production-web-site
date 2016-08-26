(ns good.production.translation
  (:require
    [good.production.state :as state]
    [taoensso.tower :as tower :include-macros true]))

(def ^:private tconfig
  {:fallback-locale :ru
   :compiled-dictionary (tower/dict-compile* "ui-translation.edn")})

(def t (tower/make-t tconfig))

(defn label [label-name]
  (t @state/locale label-name))