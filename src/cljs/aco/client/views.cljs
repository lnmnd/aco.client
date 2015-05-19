(ns aco.client.views
  (:require [re-frame.core :as re-frame :refer [dispatch subscribe]]))

(defn index-page []
  [:h2 "index page"])

(defn about-page []
  [:h2 "about page"])

(defn current-page []
  (let [active-panel (subscribe [:active-panel])]
    (fn []
      [:div
       [:h1 "Article Collector"]
       [:div
        [:a {:href "#" :on-click #(dispatch [:set-active-panel :index])} "index"]
        " "
        [:a {:href "#" :on-click #(dispatch [:set-active-panel :about])} "about"]]
       (condp = @active-panel
         :index [index-page]
         :about [about-page])])))
