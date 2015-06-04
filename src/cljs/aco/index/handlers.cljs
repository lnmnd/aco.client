(ns aco.index.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [ajax.core :refer [GET]]))

(register-handler
 :index/request-acos
 (fn [db]
   (GET
    (str "http://localhost:8080/api/article-collections")
    {:response-format :json
     :keywords? true
     :handler #(dispatch [:index/process-acos %])
     :error-handler #(dispatch [:index/process-error %])})
   (-> db
       (assoc-in [:index :error-loading] false)
       (assoc-in [:index :loading] true))))

(register-handler
 :index/process-acos
 (fn [db [_ res]]
   (-> db
       (assoc-in [:index :loading] false)
       (assoc-in [:index :acos] res))))

(register-handler
 :index/process-error
 (fn [db [_ res]]
   (-> db
       (assoc-in [:index :loading] false)
       (assoc-in [:index :error-loading] true))))
