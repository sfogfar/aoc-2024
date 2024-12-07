(ns aoc-2024.utils
  (:require
   [clojure.string :as str]))

(defn str->num-lines
  [path]
  (->> path
       (slurp)
       (str/split-lines)
       (map #(str/split % #"\s+"))
       (map #(map parse-long %))))

(defn remove-first
  [pred coll]
  (concat
   (take-while pred coll)
   (rest (drop-while pred coll))))

(defn slurp-str
  [path]
  (str/escape (slurp path) {\" "\\\""}))
