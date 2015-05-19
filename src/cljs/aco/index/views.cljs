(ns aco.index.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn index-page []
  (let [loading (subscribe [:index/loading])
        acos (subscribe [:index/acos])]
    (fn []
      [:div
       (when @loading
         [:p "Loading..."])
       [:ul (for [aco @acos]
              ^{:key (:uuid aco)}
              [:li (:title aco) " [" (:uuid aco) "]"])]])))
