(ns good.production.handler
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
    [good.production.state :as state]
    [good.production.translation :as t]
    [cljs-http.client :as http]
    [cljs.core.async :refer [<!]]))

(defn set-locale [locale]
  (reset! state/locale locale))

(defn close-alert [type]
  (reset! state/alert (assoc @state/alert type nil)))

(defn change-alert-state [type message]
  (reset! state/alert (assoc @state/alert type message)))

(defn clean-feedback-form []
  (reset! state/feedback-name nil)
  (reset! state/feedback-email nil)
  (reset! state/feedback-phone nil)
  (reset! state/feedback-body nil))

(defn send-message-handler []
  (go
    (let [response (<! (http/post
                         "http://feedback-dzer6.rhcloud.com/send"
                         {:with-credentials? false
                          :json-params {:name @state/feedback-name
                                        :email @state/feedback-email
                                        :phone @state/feedback-phone
                                        :body @state/feedback-body}}))]
      (if (= 200 (:status response))
        (change-alert-state :success (t/label :alert-success-message))
        (change-alert-state :error (t/label :alert-error-message)))
      (clean-feedback-form))))