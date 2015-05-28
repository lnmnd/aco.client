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
       (when (and (not @loading) (not @error-loading))
         [:div
          [:h2 (:title @aco)]
          [:p (take 10 (:date (:date @aco)))]
          [:p (:description @aco)]
          [:ul (for [article (:articles @aco)]
              ^{:key (:url article)}
              [:li [:a {:href "#"} (:title article)]])]])
       [:p [:a {:href "#"
                :on-click #(do (dispatch [:index/request-acos])
                               (dispatch [:set-active-panel :index]))}
            "< back"]]])))
