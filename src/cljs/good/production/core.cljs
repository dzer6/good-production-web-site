(ns good.production.core
  (:require
    [reagent.core :as reagent]
    [good.production.translation :as t]
    [good.production.state :as state]
    [good.production.handler :as h]))

(enable-console-print!)

(prn "ClojureScript appears to have loaded correctly.")

(defn locale-buttons []
  (prn "locale-buttons = " @state/locale)
  [:div.pull-right
   (if (= :en @state/locale)
     [:div.btn-group {:role "group"}
      ;[:button.btn.btn-primary {:type "button" :on-click #(h/set-locale :en)} "EN"]
      [:button.btn.btn-default {:type "button" :on-click #(h/set-locale :ru)} "RU"]]
     [:div.btn-group {:role "group"}
      ;[:button.btn.btn-default {:type "button" :on-click #(h/set-locale :en)} "EN"]
      [:button.btn.btn-primary {:type "button" :on-click #(h/set-locale :ru)} "RU"]])])

(defn header []
  [:div
   [locale-buttons]
   [:div.jumbotron
    [:h1 "Good Production"]
    [:p.lead (t/label :header)]]])

(defn featurette-left [key heading body img-src img-alt]
  [:div {:key key}
   [:div.row.featurette.vertical-align
    [:div.col-md-6
     [:h2.featurette-heading.text-right heading]
     [:p.lead.text-justify body]]
    [:div.col-md-6
     [:img.featurette-image.img-responsive.center-block.img-circle
      {:src img-src :alt img-alt}]]]
   [:hr.featurette-divider]])

(defn featurette-right [key heading body img-src img-alt]
  [:div {:key key}
   [:div.row.featurette.vertical-align
    [:div.col-md-6.col-md-push-6
     [:h2.featurette-heading.text-left heading]
     [:p.lead.text-justify body]]
    [:div.col-md-6.col-md-pull-6
     [:img.featurette-image.img-responsive.center-block.img-circle
      {:src img-src :alt img-alt}]]]
   [:hr.featurette-divider]])

(defn service-label [item index]
  (-> (str "services-" item "-" index)
      keyword
      t/label))

(defn services []
  [:div
   [:hr.featurette-divider]
   (doall
     (for [index (range state/services-number)]
       (let [heading (service-label "heading" index)
             body (service-label "body" index)
             img-src (service-label "img-src" index)
             img-alt (service-label "img-alt" index)]
         (if (odd? index)
           (featurette-left index heading body img-src img-alt)
           (featurette-right index heading body img-src img-alt)))))])

(defn portfolio []
  [:div
   [:iframe {:width "100%" :height "300" :scrolling "no" :frameBorder "no" :src state/soundcloud}]
   [:hr.featurette-divider]])

(defn contacts []
  [:div
   [:h1.text-center.portfolio (t/label :contacts)]
   [:div.row.featurette.vertical-align
    [:div.col-md-7.col-md-push-5.text-center
     [:address
      (t/label :contacts-city) [:br]
      state/contacts-phone [:br]
      [:a {:href (str "mailto:" state/contacts-email)} state/contacts-email]]]
    [:div.col-md-5.col-md-pull-7
     [:img.featurette-image.img-responsive.center-block.img-circle
      {:src state/contacts-img-src :alt state/contacts-img-alt}]]]
   [:hr.featurette-divider]])

(defn footer []
  [:footer
   [:p.pull-right [:a {:href "#"} "Back to top"]]
   [:p "© 2016 Good Production"]])

(defn app []
  [:div
   [:div.container
    [header]
    [services]
    [portfolio]
    [contacts]
    [footer]]])

;;;; Init

(defn root-div []
  (.getElementById js/document "app"))

(defn hide-spinner []
  (when-let [rd (root-div)]
    (set! (.-className rd) "")))

(hide-spinner)

(defn mount-it []
  (prn "mount-it")
  (reagent/render-component
    (fn [] [app])
    (.-body js/document)
    (fn [])))

(defn unmount-it []
  (prn "unmount-it")
  (reagent/unmount-component-at-node
    (.-body js/document)))

(mount-it)