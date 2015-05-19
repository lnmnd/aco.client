(ns aco.client.core
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :as re-frame :refer [dispatch-sync]]
            [aco.client.handlers :as handlers]
            [aco.client.subs :as subs]
            [aco.client.views :refer [current-page]]))

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (dispatch-sync [:init])
  (mount-root))
