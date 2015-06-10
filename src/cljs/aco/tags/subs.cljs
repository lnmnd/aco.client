(ns aco.tags.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
 :tags/tags
 (fn [db]
   (let [tags (reaction (:tags @db))]
     (reaction (:tags @tags)))))

(register-sub
 :tags/loading
 (fn [db]
   (let [tags (reaction (:tags @db))]
     (reaction (:loading @tags)))))


(register-sub
 :tags/error-loading
 (fn [db]
   (let [tags (reaction (:tags @db))]
     (reaction (:error-loading @tags)))))

(register-sub
 :tags/selected-tag
 (fn [db]
   (let [tags (reaction (:tags @db))]
     (reaction (:selected-tag @tags)))))
