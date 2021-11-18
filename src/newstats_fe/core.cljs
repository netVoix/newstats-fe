(ns newstats-fe.core
  (:require
   [kee-frame.core :as k]
   [kee-frame.event-logger :as event-logger]
   [kee-frame.error :as error]
   [newstats-fe.db :as db]
   [newstats-fe.views.main :as main]
   [newstats-fe.events]
   [newstats-fe.views.home :as home]
   [newstats-fe.views.top :as top]
   [newstats-fe.views.archieve :as archieve]
   [newstats-fe.views.bans :as bans]))

(goog-define debug false)

(enable-console-print!)

(def routes [["/" :home]
             ["/top" :top]
             ["/archieve" :archieve]
             ["/bans" :bans]])

(defn error-body [[err info]]
  (js/console.log "An error occurred: " info)
  (js/console.log "Context: " err)
  [:div "Something went wrong"])

(defn dispatch-main []
  [error/boundary
   error-body
   [k/case-route (comp :name :data)
    :home [home/view]
    :top [top/view]
    :archieve [archieve/view]
    :bans [bans/view]
    [:div "Loading..."]]])

(defn render! []
  (k/start! {:log                 {:level        :info
                                   :ns-blacklist ["kee-frame.event-logger"]}
             :route-change-event  :route-changed
             :global-interceptors [event-logger/interceptor]
             :not-found           "/"
             :screen              true
             :scroll              false
             :routes              routes
             :hash-routing?       false
             :initial-db          db/initial-db
             :root-component      [main/view [dispatch-main]]
             :app-db-spec         ::db/db-spec}))
