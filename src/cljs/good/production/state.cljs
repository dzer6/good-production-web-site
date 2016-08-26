(ns good.production.state
  (:require
    [reagent.core :as reagent]))

(def locale (reagent/atom :ru))

(def header "Мы музыканты, композиторы, аранжировщики, звукорежиссеры, саунд-дизайнеры и просто люди, которые знают «как». Мы те, кто посредством искусства, всегда найдет путь к сердцу. От ноты к симфонии. От идеи к воплощению. От сердца к сердцу.")

(def services-number 4)

(def soundcloud "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/users/3262970&amp;auto_play=false&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;visual=true")

(def contacts-phone "+380632473137")

(def contacts-email "productiongood@gmail.com")

(def contacts-img-src "img/kiev.jpg")

(def contacts-img-alt "Kiev")

(def alert (reagent/atom {}))

