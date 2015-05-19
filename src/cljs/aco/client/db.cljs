(ns aco.client.db
  (:require [aco.index.db :as index]))

(def default-value
  {:active-panel :index
   :index index/default-value})
