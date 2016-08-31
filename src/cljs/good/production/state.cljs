(ns good.production.state
  (:require
    [reagent.core :as reagent]))

(def locale (reagent/atom :ru))

(def services-number 5)

(def soundcloud "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/users/3262970&amp;color=ff5500&amp;auto_play=false&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false")

(def contacts-phone "+380632473137")

(def contacts-email "info@good-production.com")

(def alert (reagent/atom {}))

