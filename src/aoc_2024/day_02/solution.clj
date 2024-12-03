(ns aoc-2024.day-02.solution
  (:gen-class)
  (:require
   [aoc-2024.utils :as utils]))

(def input-path  "src/aoc_2024/day_02/input.txt")
(def reports (utils/get-lines input-path))

(defn inc?
  [report]
  (apply < report))

(defn dec?
  [report]
  (apply > report))

(defn report->steps
  [report]
  (let [pairs (partition 2 1 report)]
    (map (fn [[a b]] (abs (- a b))) pairs)))

(defn small-step?
  [step]
  (and (>= step 1) (<= step 3)))

(defn gradual?
  [report]
  (let [steps (report->steps report)]
    (every? small-step? steps)))

(defn safe?
  [report]
  (and
   (or (inc? report) (dec? report))
   (gradual? report)))

(defn count-safe-reports
  [reports pred]
  (let [safe-reports (filter pred reports)]
    (count safe-reports)))

(defn all-report-variants [report]
  (cons report (map #(concat (take % report) (drop (inc %) report))
                    (range (count report)))))

(defn dampened-safe?
  [report]
  (some safe? (all-report-variants report)))

(def part-one-solution (count-safe-reports reports safe?)) ; 279
(def part-two-solution (count-safe-reports reports dampened-safe?)) ; 343

(defn -main []
  (println "**")
  (println "Day Two")
  (println "Part One" part-one-solution)
  (println "Part Two" part-two-solution))
