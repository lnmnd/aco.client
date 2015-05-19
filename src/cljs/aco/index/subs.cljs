(ns aco.index.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
 :index/loading
 (fn [db]
   (let [index (reaction (:index @db))]
     (reaction (:loading @index)))))

(register-sub
 :index/error-loading
 (fn [db]
   (let [index (reaction (:index @db))]
     (reaction (:error-loading @index)))))

(register-sub
 :index/acos
 (fn [db]
   (let [index (reaction (:index @db))]
     (reaction (:acos @index)))))
