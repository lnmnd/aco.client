(ns aco.single.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
 :single/loading
 (fn [db]
   (let [single (reaction (:single @db))]
     (reaction (:loading @single)))))

(register-sub
 :single/error-loading
 (fn [db]
   (let [single (reaction (:single @db))]
     (reaction (:error-loading @single)))))

(register-sub
 :single/aco
 (fn [db]
   (let [single (reaction (:single @db))]
     (reaction (:aco @single)))))
