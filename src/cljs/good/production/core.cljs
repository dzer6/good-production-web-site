(ns good.production.core
  (:require
    [reagent.core :as reagent]
    [good.production.state :as state]
    [good.production.handler :as h]))

(enable-console-print!)

(prn "ClojureScript appears to have loaded correctly.")

(defn header []
  [:div.jumbotron
   [:h1 "Good Production"]
   [:h2 "Music&Sound"]
   [:p.lead state/header]])

(defn featurette-left [key {:keys [heading body img-src img-alt]}]
  [:div {:key key}
   [:div.row.featurette
    [:div.col-md-7
     [:h2.featurette-heading heading]
     [:p.lead.text-justify body]]
    [:div.col-md-5
     [:img.featurette-image.img-responsive.center-block.img-circle
      {:src img-src :alt img-alt :width 500 :height 500}]]]
   [:hr.featurette-divider]])

(defn featurette-right [key {:keys [heading body img-src img-alt]}]
  [:div {:key key}
   [:div.row.featurette
    [:div.col-md-7.col-md-push-5
     [:h2.featurette-heading heading]
     [:p.lead.text-justify body]]
    [:div.col-md-5.col-md-pull-7
     [:img.featurette-image.img-responsive.center-block.img-circle
      {:src img-src :alt img-alt :width 500 :height 500}]]]
   [:hr.featurette-divider]])

(defn services []
  [:div
   [:hr.featurette-divider]
   [:h1.text-center.services state/services-header]
   (for [[item index] (map vector state/services (-> state/services count range))]
     (if (odd? index)
       (featurette-left index item)
       (featurette-right index item)))])

(defn portfolio []
  [:div
   [:h1.text-center.portfolio state/portfolio]
   [:iframe {:width "100%" :height "300" :scrolling "no" :frameBorder "no" :src state/soundcloud}]
   [:hr.featurette-divider]])

(defn alert-block []
  (prn "alert-block, @state/alert =" @state/alert)
  (cond
    (:error   @state/alert) [:div.alert.alert-danger
                             [:button.close {:type "button" :on-click #(h/close-alert :error)}   "×"]
                             (:error   @state/alert)]
    (:success @state/alert) [:div.alert.alert-success
                             [:button.close {:type "button" :on-click #(h/close-alert :success)} "×"]
                             (:success @state/alert)]
    :else                   [:div]))

(defn feedback []
  [:form
   [:h3 state/feedback-title]
   [:div.form-group
    [:input.form-control {:type        :text
                          :placeholder state/feedback-name-placeholder
                          :value       @state/feedback-name
                          :on-change   #(reset! state/feedback-name (-> % .-target .-value))}]]
   [:div.form-group
    [:input.form-control {:type        :text
                          :placeholder state/feedback-email-placeholder
                          :value       @state/feedback-email
                          :on-change   #(reset! state/feedback-email (-> % .-target .-value))}]]
   [:div.form-group
    [:input.form-control {:type        :text
                          :placeholder state/feedback-phone-placeholder
                          :value       @state/feedback-phone
                          :on-change   #(reset! state/feedback-phone (-> % .-target .-value))}]]
   [:div.form-group
    [:textarea.form-control {:rows      6
                             :value     @state/feedback-body
                             :on-change #(reset! state/feedback-body (-> % .-target .-value))}]]
   [alert-block]
   [:div.form-group
    [:input.btn {:type :button
                 :value state/feedback-send
                 :on-click h/send-message-handler}]]])

(defn contacts []
  [:div
   [:h1.text-center.portfolio state/contacts]
   [:div.row.featurette
    [:div.col-md-7.col-md-push-5
     [:address
      [:strong "Good Production"] [:br]
      state/contacts-city [:br]
      state/contacts-phone [:br]
      [:a {:href (str "mailto:" state/contacts-email)} state/contacts-email]]
     [feedback]]
    [:div.col-md-5.col-md-pull-7
     [:img.featurette-image.img-responsive.center-block.img-circle
      {:src state/contacts-img-src :alt state/contacts-img-alt}]]]
   [:hr.featurette-divider]])

(defn footer []
  [:footer
   [:p.pull-right [:a {:href "#"} "Back to top"]]
   [:p "© 2013 - 2015 Good Production"]])

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