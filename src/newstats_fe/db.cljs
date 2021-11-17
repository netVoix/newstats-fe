(ns newstats-fe.db
  (:require [cljs.spec.alpha :as s]))

(def initial-db {})

(s/def ::db-spec (s/keys :req-un []))
