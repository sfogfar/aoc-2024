(ns aoc-2024.day-01.solution
  (:gen-class) 
  (:require
   [aoc-2024.utils :as utils]))

(defn get-list
  [lines position]
  (->> lines
       (map #(position %))
       (sort)))

(defn get-distance
  [[a b]]
  (abs (- a b)))
 
(defn get-total-distance
  [lines]
  (let [list-1 (get-list lines first)
        list-2 (get-list lines second)
        zipped-list (map vector list-1 list-2)
        distances (map get-distance zipped-list)]
    (apply + distances)))

(defn get-similarity-score
  [lines]
  (let [list-1 (get-list lines first)
        list-2 (get-list lines second)
        list-2-freqs (frequencies list-2)
        similarities (map (fn [num]
                            (let [num-freq (get list-2-freqs num 0)]
                              (* num num-freq))) list-1)]
    (apply + similarities)))

(def input-path  "src/aoc_2024/day_01/input.txt")
(def lines (utils/get-lines input-path))
(def part-one-solution (get-total-distance lines)) ; 1223326
(def part-two-solution (get-similarity-score lines)) ; 21070419

(defn -main []
  (println "*")
  (println "Day One")
  (println "Part One" part-one-solution)
  (println "Part Two" part-two-solution))
