(ns aco.tags.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn tags-page []
  (let [tags (subscribe [:tags/tags])
        selected-tag (subscribe [:tags/selected-tag])
        loading (subscribe [:tags/loading])
        error-loading (subscribe [:tags/error-loading])]
    (fn []
      [:div
       (when @loading
         [:p.bg-info "Loading..."])
       (when @error-loading
         [:p.bg-danger "Error loading data"])
       [:ul (doall (for [tag @tags]
                     ^{:key (:name tag)}
                     [:li (if (and @selected-tag
                                   (= @selected-tag (:name tag)))
                            [:div
                             [:strong (:name tag)]
                             (when (:acos tag)
                               [:ul (for [aco (:acos tag)]
                                      ^{:key (:uuid aco)}
                                      [:li [:a {:href "#" :on-click #(do (dispatch [:single/request-aco (:uuid aco)])
                                                                         (dispatch [:set-active-panel :single]))}
                                            (:title aco)]])])]
                            [:a {:href "#"
                                 :on-click #(do (dispatch [:tags/set-selected-tag (:name tag)])
                                                (dispatch [:tags/request-tag-acos (:name tag)]))}
                             (:name tag)])]))]])))
