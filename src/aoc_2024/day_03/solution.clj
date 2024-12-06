(ns aoc-2024.day-03.solution
  (:gen-class)
  (:require
   [aoc-2024.utils :refer [slurp-str]]))

(def input-path  "src/aoc_2024/day_03/input.txt")
(def memory (slurp-str input-path))

(defn extract-instructions
  [memory]
  (let [instruction-pattern #"mul\(\d+,\d+\)"]
    (re-seq instruction-pattern memory)))

(defn follow-instructions
  [instructions]
  (let [integer-pattern #"\d+"]
    (->> instructions
         (map #(re-seq integer-pattern %))
         (map (fn [coll] (map parse-long coll)))
         (map (fn [[a b]] (* a b))))))

(defn execute-corrupt-program
  [memory]
  (->> memory
       (extract-instructions)
       (follow-instructions)
       (apply +)))

(defn extract-detailed-instructions
  [memory]
  (let [detailed-instruction-pattern #"(?:mul\(\d+,\d+\))|(?:do\(\)|don't\(\))"]
    (re-seq detailed-instruction-pattern memory)))

(defn extract-valid-instructions
  [instructions]
  (loop [instructions instructions valid-instructions []]
    (let [x (first instructions)
          xs (rest instructions)
          do "do()"
          don't "don't()"]
      (cond
        (empty? instructions) valid-instructions
        (= x do) (recur xs valid-instructions)
        (= x don't) (recur (drop-while #(not= do %) xs) valid-instructions)
        :else (recur xs (conj valid-instructions x))))))

(defn execute-corrupt-detailed-program
  [memory]
  (->> memory
       (extract-detailed-instructions)
       (extract-valid-instructions)
       (follow-instructions)
       (apply +)))

(def part-one-solution (execute-corrupt-program memory)) ; 163931492
(def part-two-solution (execute-corrupt-detailed-program memory))  ; 76911921

(defn -main []
  (println "***")
  (println "Day Three")
  (println "Part One" part-one-solution)
  (println "Part Two" part-two-solution))
