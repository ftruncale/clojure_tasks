(ns ch03.exercises
  (:gen-class))

;Use the str, vector, list, hash-map, and hash-set functions.
(str "I guess we should combine this" "and THIS")

(vector 1 2 3 4 "Interesting")
[1 2 3 4 "Interesting"]

(hash-map :key 1 :potato 2)
{:key 1 :potato 2}

(hash-set 1 2 3 4 "cause")
#{1 2 3 4 "cause"}

;Write a function that takes a number and adds 100 to it.
(defn add-100
  "Adds 100 to given number"
  [base]
  (+ base 100))

(add-100 5)
;=> 105

; Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction: 
(defn dec-maker
  "Makes a dec function of chosen size"
  [size]
  (fn [x] (- x size)))

(defn dec-maker-alt 
  "Makes a dec function of the chosen size, using alt anonymous function"
  [size]
  #(- % size))

(def dec9 (dec-maker 9))
(dec9 10)

(def dec9-alt (dec-maker-alt 9))
(dec9-alt 10)

;Write a function, mapset, that works like map except the return value is a set: 
(defn mapset 
  "Maps function to values, only it's a set
   Takes function to apply and vector to apply to"
  [func vec]
  (set (map func vec))
  )

(mapset inc [1 1 2 2])

;Create a function that’s similar to symmetrize-body-parts except 
;that it has to work with weird space aliens with radial symmetry. 
;Instead of two eyes, arms, legs, and so on, they have five.
; [Skipped in favor of generalized function]

;Hobbit vector to work with
(def asym-hobbit-body-parts [{:name "head" :size 3}
  {:name "left-eye" :size 1}
  {:name "left-ear" :size 1}
  {:name "mouth" :size 1}
  {:name "nose" :size 1}
  {:name "neck" :size 2}
  {:name "left-shoulder" :size 3}
  {:name "left-upper-arm" :size 3}
  {:name "chest" :size 10}
  {:name "back" :size 10}
  {:name "left-forearm" :size 3}
  {:name "abdomen" :size 6}
  {:name "left-kidney" :size 1}
  {:name "left-hand" :size 2}
  {:name "left-knee" :size 2}
  {:name "left-thigh" :size 4}
  {:name "left-lower-leg" :size 3}
  {:name "left-achilles" :size 1}
  {:name "left-foot" :size 2}])

;strips part of designator
(defn strip-part
  [part addnum]
  (if (re-find #"^left-" "" (:name part))
    (addnum 
      {
        :name (clojure.string/replace (:name part) #"^left-" "")
        :size (:size part) 
      })
    part
  ))
