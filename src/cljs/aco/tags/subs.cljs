(ns aco.tags.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
 :tags/tags
 (fn [db]
   (let [tags (reaction (:tags @db))]
     (reaction (:tags @tags)))))
