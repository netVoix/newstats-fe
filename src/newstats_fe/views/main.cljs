(ns newstats-fe.views.main
  (:require
   [newstats-fe.styles :as styles]
   [kee-frame.core :as k]
   [re-com.core :as rc]))

(defn link [label route icon]
  [:a
   {:class (styles/header-link) :href (k/path-for [route])}
   [rc/md-icon-button :md-icon-name icon :class (styles/header-link-icon)]
   label])

(defn header []
  [:div {:class (styles/header)}
   [:div {:class "container"}
    [rc/h-box
     :children [[rc/box :child [link "Home" :home "zmdi-home"]]
                [rc/box :child [link "Top" :top "zmdi-thumb-up-down"]]
                [rc/box :child [link "Archieve" :archieve "zmdi-book"]]
                [rc/box :child [link "Bans" :bans "zmdi-alert-triangle"]]]
     :gap "30px"]]])

(defn view [main]
  [:div
   [header]
   [:div {:class (styles/content)} [:div {:class "container"} main]]])
