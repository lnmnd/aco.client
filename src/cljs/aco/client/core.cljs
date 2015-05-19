(ns aco.client.core
    (:require [reagent.core :as reagent :refer [atom]]))

(defn current-page []
  [:div [:h1 "aco.client"]])

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
