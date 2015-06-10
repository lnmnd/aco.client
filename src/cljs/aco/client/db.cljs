(ns aco.client.db
  (:require [aco.index.db :as index]
            [aco.tags.db :as tags]))

(def default-value
  {:active-panel :index
   :index index/default-value
   :tags tags/default-value})
