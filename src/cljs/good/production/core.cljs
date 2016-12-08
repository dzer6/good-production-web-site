(ns good.production.core
  (:require
    [reagent.core :as reagent]
    [good.production.translation :as t]
    [good.production.state :as state]
    [good.production.handler :as h]))

(enable-console-print!)

(prn "ClojureScript appears to have loaded correctly.")

(defn locale-buttons []
  [:div.pull-right
   (if (= :en @state/locale)
     [:div.btn-group.btn-group-xs {:role "group"}
      [:button.btn.btn-primary {:type "button" :on-click #(h/set-locale :en)} "EN"]
      [:button.btn.btn-default {:type "button" :on-click #(h/set-locale :ru)} "RU"]]
     [:div.btn-group.btn-group-xs {:role "group"}
      [:button.btn.btn-default {:type "button" :on-click #(h/set-locale :en)} "EN"]
      [:button.btn.btn-primary {:type "button" :on-click #(h/set-locale :ru)} "RU"]])])

(defn header []
  [:div.header.clearfix
   [locale-buttons]
   [:img.pull-left {:src "img/good-production.png" :alt "Good Production"}]])

(defn jumbotron []
  [:div.jumbotron
   [:p.lead (t/label :header)]])

(defn featurette-left [key heading body img-src img-alt]
  [:div {:key key}
   [:div.row.featurette.vertical-align
    [:div.col-md-6
     [:h3.featurette-heading.text-left heading]
     [:p body]]
    [:div.col-md-6
     [:img.featurette-image.center-block.img-circle
      {:src img-src :alt img-alt :width "274" :height "274"}]]]
   [:hr.featurette-divider]])

(defn featurette-right [key heading body img-src img-alt]
  [:div {:key key}
   [:div.row.featurette.vertical-align
    [:div.col-md-6.col-md-push-6
     [:h3.featurette-heading.text-left heading]
     [:p body]]
    [:div.col-md-6.col-md-pull-6
     [:img.featurette-image.center-block.img-circle
      {:src img-src :alt img-alt :width "274" :height "274"}]]]
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
   [:iframe {:width "100%" :height "400" :scrolling "no" :frameBorder "no" :src state/soundcloud}]
   [:hr.featurette-divider]])

(defn contacts []
  [:div
   [:div.row.featurette.vertical-align
    [:div.col-md-7.col-md-push-5
     [:div.row
      [:div.col-md-11.col-md-push-1
       [:address
        (t/label :contacts-city) [:br]
        state/contacts-phone [:br]
        [:a {:href (str "mailto:" state/contacts-email)} state/contacts-email]]]]]
    [:div.col-md-5.col-md-pull-7
     [:img.featurette-image.center-block.img-circle
      {:src "img/kiev.jpg" :alt "Kiev" :width "274" :height "274"}]]]
   [:hr.featurette-divider]])

(defn footer []
  [:footer
   [:p "© 2016 Good Production"]])

(defn app []
  [:div.container
   [header]
   [jumbotron]
   [services]
   [portfolio]
   [contacts]
   [footer]])

;;;; Init

(defn root-div []
  (.getElementById js/document "app"))

(defn hide-spinner []
  (set! (.-className (root-div)) "app")) ; TODO function name not fully corresponds its functionality

(defn mount-it []
  (prn "mount-it")
  (reagent/render-component
    (fn []
      (hide-spinner)
      [app])
    (root-div)
    (fn [])))

(defn unmount-it []
  (prn "unmount-it")
  (reagent/unmount-component-at-node
    (root-div)))

(mount-it)
