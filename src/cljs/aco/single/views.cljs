(ns aco.single.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn- find-by-url [url articles]
  (reduce (fn [article found]
            (if (= url (:url article))
              article
              found)) articles))

(defn single-page []
  (let [loading (subscribe [:single/loading])
        error-loading (subscribe [:single/error-loading])
        aco (subscribe [:single/aco])
        selected-article (subscribe [:single/selected-article])]
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
                 [:li [:a {:href "#" :on-click #(dispatch [:single/set-selected-article (:url article)])}
                       (if (= @selected-article (:url article))
                         [:strong (:title article)]
                         (:title article))]])]
          (when @selected-article
            (let [article (find-by-url @selected-article (:articles @aco))]
              [:div
               [:h2 (:title article)]
               [:pre (:url article)]
               [:div {:dangerouslySetInnerHTML {:__html (:content article)}}]]))])
       [:p [:a {:href "#"
                :on-click #(do (dispatch [:index/request-acos])
                               (dispatch [:set-active-panel :index]))}
            "< back"]]])))
