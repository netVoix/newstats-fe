(ns newstats-fe.styles
  (:require
   [spade.core   :refer [defglobal defclass]]))

(defglobal defaults)

(defclass header []
  {:background-color "#222"
   :border-radius "4px"
   :color :white
   :padding "20px"})

(defclass content []
  {:padding-top "20px"})

(defclass header-link []
  {:color "#777"
   :display :flex
   :align-items :center
   :font-size "20px"
   :font-weight 600}
  [:&:hover {:color :white
             :text-decoration :none}])

(defclass header-link-icon []
  {:margin-right "5px"})
