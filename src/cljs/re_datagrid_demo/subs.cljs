(ns re-datagrid-demo.subs
  (:require

   [reagent.core :as r]
   [re-frame.core :as rf]))


(rf/reg-sub
 :data
 (fn [db _]
   (:data db)))


(rf/reg-sub
 :my-loading
 (fn [_]
   false))
