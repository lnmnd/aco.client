(ns aco.single.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn single-page []
  (let [loading (subscribe [:single/loading])
        error-loading (subscribe [:single/error-loading])
        aco (subscribe [:single/aco])]
    (fn []
      [:div
       (when @loading
         [:p.bg-info "Loading..."])
       (when @error-loading
         [:p.bg-danger "Error loading data"])
       [:h2 (:title @aco)]
       [:p (take 10 (:date (:date @aco)))]
       [:p (:description @aco)]
       [:p [:a {:href "#"
                :on-click #(do (dispatch [:index/request-acos])
                               (dispatch [:set-active-panel :index]))}
            "< back"]]])))
