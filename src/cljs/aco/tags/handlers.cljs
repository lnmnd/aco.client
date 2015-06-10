(ns aco.tags.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [ajax.core :refer [GET]]))

(register-handler
 :tags/request-tags
 (fn [db]
   (GET
    (str "http://localhost:8080/api/tags")
    {:response-format :json
     :keywords? true
     :handler #(dispatch [:tags/process-tags %])
     :error-handler #(dispatch [:tags/process-error %])})
   (-> db
       (assoc-in [:tags :error-loading] false)
       (assoc-in [:tags :loading] true))))


(register-handler
 :tags/process-tags
 (fn [db [_ res]]
   (-> db
       (assoc-in [:tags :loading] false)
       (assoc-in [:tags :tags] (map (fn [tag]
                                      {:name tag
                                       :acos nil}) res)))))

(register-handler
 :tags/process-error
 (fn [db [_ res]]
   (-> db
       (assoc-in [:tags :loading] false)
       (assoc-in [:tags :error-loading] true))))

(register-handler
 :tags/request-tag-acos
 (fn [db [_ tag]]
   (GET
    (str "http://localhost:8080/api/tags/" tag)
    {:response-format :json
     :keywords? true
     :handler #(dispatch [:tags/process-tag-acos tag %])
     :error-handler #(dispatch [:tags/process-error %])})
   (-> db
       (assoc-in [:tags :error-loading] false)
       (assoc-in [:tags :loading] true))))

(register-handler
 :tags/process-tag-acos
 (fn [db [_ tag res]]
   (println "process" tag)
   (println res)
   (-> db
       (assoc-in [:tags :loading] false))))
