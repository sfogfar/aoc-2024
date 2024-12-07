(ns aoc-2024.day-04.solution
  (:gen-class)
  (:require
   [clojure.core.matrix :as m]
   [clojure.string :as str]))

(def input-path  "src/aoc_2024/day_04/test-input.txt")

(defn input->lines
  [path]
  (->> path
       slurp
       str/split-lines))

(defn input->cols
  [path]
  (->> path
       slurp
       (str/split-lines)
       (map seq)
       (apply mapv vector)
       (mapv (partial apply str))))

(def ex-matrix
  [[1 2 3]
   [4 5 6]
   [7 8 9]])

(defn ->matrix
  [input-path]
  (->> input-path 
        slurp
        (str/split-lines)
        (map seq)))

(defn ->columns
  [matrix]
  (apply mapv vector matrix))

(defn expose-diagonals
  "Reform the matrix to expose diagonals 
  in columns for easier access."
  [matrix]
  (map-indexed 
    (fn [idx row]
      (let [left-pad idx
            right-pad (- (count matrix) idx 1)]
        (vec (concat 
               (repeat left-pad nil)
               row
               (repeat right-pad nil)))))
    matrix))

(defn ->fwd-diagonals
  [matrix]
  (->> matrix
     (expose-diagonals)
     (apply mapv vector)
     (map #(remove nil? %))))

(defn ->bkwd-diagonals
  [matrix]
  (->> matrix
       (map reverse)
       ->fwd-diagonals))

(->fwd-diagonals ex-matrix)
(->bkwd-diagonals ex-matrix)

(expose-diagonals ex-matrix)

(def foo (map-indexed (fn [i v]
                        (concat (repeat i nil) v))
                      ex-matrix))

(def bar (reverse foo))

(->> bar
     (apply mapv vector)) ; [[nil nil 1] [nil 4 2] [7 5 3]]

[[1 2 3]
 [0 4 5 6]
 [0 0 7 8 9]]

(get-diagonals ex-matrix)

(input->rows input-path)


;; get lines
;; get columns
;; get diagonals

(defn str->3d-array
  [path]
  (->> path
       (slurp)
       (str/split-lines)
       (map #(str/split % #"\s+"))
       (map #(map parse-long %))))

(defn -main []
  (println "****")
  (println "Day Four")
  #_(println "Part One" part-one-solution)
  #_(println "Part Two" part-two-solution))
