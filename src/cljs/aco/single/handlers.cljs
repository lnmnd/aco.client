(ns aco.single.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [ajax.core :refer [GET]]))

(register-handler
 :single/request-aco
 (fn [db [_ uuid]]
   (GET
    (str "http://localhost:8080/api/article-collections/" uuid)
    {:response-format :json
     :keywords? true
     :handler #(dispatch [:single/process-aco %])
     :error-handler #(dispatch [:single/process-error %])})
   (-> db
       (assoc-in [:single :loading] true)
       (assoc-in [:single :selected-article] nil))))

(register-handler
 :single/process-aco
 (fn [db [_ res]]
   (-> db
       (assoc-in [:single :loading] false)
       (assoc-in [:single :aco] res))))

(register-handler
 :single/process-error
 (fn [db [_ res]]
   (-> db
       (assoc-in [:single :loading] false)
       (assoc-in [:single :error-loading] true))))

(register-handler
 :single/set-selected-article
 (fn [db [_ index]]
   (assoc-in db [:single :selected-article] index)))
