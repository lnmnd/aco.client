(ns aco.client.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
 :active-panel
 (fn [db]
   (reaction (:active-panel @db))))
