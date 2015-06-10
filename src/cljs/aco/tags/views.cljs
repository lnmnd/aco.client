(ns aco.tags.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn tags-page []
  (let [tags (subscribe [:tags/tags])
        loading (subscribe [:tags/loading])
        error-loading (subscribe [:tags/error-loading])]
    (fn []
      [:div
       (when @loading
         [:p.bg-info "Loading..."])
       (when @error-loading
         [:p.bg-danger "Error loading data"])
       [:ul (for [tag @tags]
              ^{:key (:name tag)}
              [:li [:a {:href "#"
                        :on-click #(dispatch [:tags/request-tag-acos (:name tag)])}
                    (:name tag)]])]])))
