(ns api.db
  (:require [monger.core :as core]
            [monger.collection :as coll]))


(defn connect []
  (core/connect))


(defn add [document]
  (let [conn (connect)
        db (core/get-db conn "test-db")]
    (->> (assoc document :uuid (java.util.UUID/randomUUID))
         (coll/insert db "test-documents"))
    "document inserted successfuly"))

(defn get-all []
  (let [conn (connect)
        db (core/get-db conn "test-db")]
      (coll/find-maps db "test-documents" {} {:_id 0})))