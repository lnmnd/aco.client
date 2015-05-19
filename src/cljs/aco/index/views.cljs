(ns aco.index.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn index-page []
  (let [loading (subscribe [:index/loading])
        error-loading (subscribe [:index/error-loading])
        acos (subscribe [:index/acos])]
    (fn []
      [:div
       (when @loading
         [:p "Loading..."])
       (when @error-loading
         [:p "Error loading data"])
       [:ul (for [aco @acos]
              ^{:key (:uuid aco)}
              [:li (:title aco) " [" (:uuid aco) "]"])]])))
