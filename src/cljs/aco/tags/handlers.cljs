(ns aco.tags.handlers
  (:require [re-frame.core :refer [register-handler dispatch]]
            [ajax.core :refer [GET]]))

(register-handler
 :tags/request-tags
 (fn [db]
   (-> db
       (assoc-in [:tags :error-loading] false)
       (assoc-in [:tags :loading] true))))
