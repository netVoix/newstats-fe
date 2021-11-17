(ns newstats-fe.core
  (:require
   [kee-frame.core :as k]
   [kee-frame.event-logger :as event-logger]
   [kee-frame.error :as error]
   [newstats-fe.db :as db]
   [newstats-fe.views.main :as main]
   [newstats-fe.events]))

(goog-define debug false)

(enable-console-print!)

(def routes [["/" :home]])

(defn error-body [[err info]]
  (js/console.log "An error occurred: " info)
  (js/console.log "Context: " err)
  [:div "Something went wrong"])

(defn dispatch-main []
  [error/boundary
   error-body
   [k/case-route (comp :name :data)
    :home [:div "This is home page"]
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
