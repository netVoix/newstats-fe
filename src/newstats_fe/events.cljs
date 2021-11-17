(ns newstats-fe.events
  (:require
   [re-frame.core :as rf]
   [day8.re-frame.tracing :refer-macros [fn-traced]]))


(rf/reg-event-fx :route-changed
                 (fn-traced [_ [_ route]]
                            {:dispatch [:kee-frame.router/route-changed route]}))
