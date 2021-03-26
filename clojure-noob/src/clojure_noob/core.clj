(ns clojure-noob.core
  (:gen-class))

;; (defn -main
;;   "I don't do a whole lot ... yet."
;;   [& args]
;;   (println "I'm a little teapot!"))

;; (defn train
;;   []
;;   (println "Choo choo!"))

;; somehow better than reassigning. okay.
(defn error-message
  [severity]
  (str "OH GOD! NOOOOO"
    (if (=severity :mild)
    "MILDLY INCONVEINECNED!"
    "DOOMED!")))

(error-message :mild)

;;do things chapter

(or + -)
((or + -) 1 2 3)
((and (= 1 1) +) 1 2 3)
((first [+ 0]) 1 2 3)

(map inc [0 1  2 3 4])

(+ (inc 199) (/ 100 (- 7 2)))

(defn too-enthusiastic
  "Return PAIN and SUFFERING enthusiastically"
  [name]
  (str "OH. NOOOO " name "YOU ARE A POTATO"))

(too-enthusiastic "FUCK")

(or + -)

(defn no-params
  []
  "I take no parameters!")

  (defn no-params
    []
    "I take no parameters!")
  (defn one-param
    [x]
    (str "I take one parameter: " x))
  (defn two-params
    [x y]
    (str "Two parameters! That's nothing! Pah! I will smoosh them "
    "together to spite you! " x y))


(defn multi-arity
  ;; 3-arity arguments and body
  ([first-arg second-arg third-arg]
      (do-things first-arg second-arg third-arg))
  ;; 2-arity arguments and body
  ([first-arg second-arg]
      (do-things first-arg second-arg))
  ;; 1-arity arguments and body
  ([first-arg]
      (do-things first-arg)))


(defn x-chop
"Describe the kind of chop you're inflicting on someone"
([name chop-type]
(str "I " chop-type " chop " name "! Take that!"))
([name]
(x-chop name "karate")))

(defn weird-arity
([]
"Destiny dressed you this morning, my friend, and now Fear is
trying to pull off your pants. If you give up, if you give in,
you're gonna end up naked with Fear just standing there laughing
at your dangling unmentionables! - the Tick")
([number]
(inc number)))
  
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(defn multiple-things
  [name & things] ;Should make parameter name and list of additional parameters  called things
  (str "Hi, " name ", here are my favorite things: "
    (clojure.string/join ", " things) ;clojure function to join a list into a string with delimeter
    )) 

(multiple-things "Potato" "potato" "oh no" "ah heck")

;;Destructuring

(defn my-first-destructured-function
  [[first-thing]] ; a vector!
  first-thing)

(defn chooser
  [[first-choice second-choice & other-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "The rest of your choices are irrelevant. "
                "Here they are anyway: "
                (clojure.string/join ", " other-choices))))

(chooser ["Test1", "Test2", "Test3", "Testy Testerson"])

;Destructuring using a map
(defn announce-treasure-location 
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}] ;:as allows you to access the original map
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

; clojure returns last form evaluated
(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

(defn number-comment 
  [x]
  (if (> x 6)
    "oh my gosh, that's a big boi!!"
    "that's a tiny boi"))

(number-comment 5)
(number-comment 7)

;;Anonymous Functions

(fn [param-list]
  "bababaaaaaaa")

(map (fn [name] (str "Hi, " name))
  ["Darth Vader" "Suppose so"])

((fn [x] (* x 3)) 8)

;not so anonymous anymore
(def my-special-multiplier (fn [x] (* x 3)))
(my-special-multiplier 36)

;compact anonymous function.. something about reader macros
(#(* % 3) 8)

;pass as map argument
(map #(str "Hi, " %)
["Buddy Pal Chum" "And That Guy"])

;function call
(* 8 3)

;anon function
#(* % 3)

;% indicates argument passed
(#(str %1 " and " %2) "cornbread" "butter beans")

; can use rest parameter '%&'
(#(identity %&) 1 "blarg" :yip)

; #  is visually compact, fn is better for complex functions

;Shows that returned functions can access variables in outer function scope
(defn inc-maker
  "Create a custom incrementor, because testing"
  [inc-by]
  #(+ % inc-by))

;define returned anon function as inc3
(def inc3 (inc-maker 3))

(inc3 7)


;;Let's... make a hobbit
(def asym-hobbit--body-parts [{:name "head"     :size 3}
                              {:name "left-eye" :size 1}
                              {:name "left-ear" :size 1}
                              {:name "mouth"    :size 1}
                              {:name "nose"     :size 1}
                              {:name "neck"     :size 2}
                              {:name "left-shoulder"  :size 3}
                              {:name "left-upper-arm" :size 3}
                              {:name "chest"    :size 3}
                              {:name "back"     :size 10}
                              {:name "left-forearm"   :size 3}
                              {:name "abdomen"        :size 6}
                              {:name "left-kidney"    :size 1}
                              {:name "left-hand"      :size 2}
                              {:name "left-knee"      :size 2}
                              {:name "left-thigh"     :size 4}
                              {:name "left-lower-leg" :size 3}
                              {:name "left-achilles"  :size 1}
                              {:name "left-foot"      :size 2}])

  
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a vector of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts ;return final-body-parts when out of parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
          (into final-body-parts
            (set  [part (matching-part part)]))))
    )
    ))

;let
(let [x 3] x)

(def dalmation-list
  ["pingo" "pongo" "bingo" "bongo"])

(let [dalmatians (take 2 dalmation-list)] dalmations)

(def x 0)
(let [x 1] x)
(let [x (inc x)] x)

;can still use rest parameter &
(let [[pingo & dalmations] dalmation-list]
  [pingo dalmations])

(loop [iteration 0]
  (println (str "Itereation " iteration))
  (if (> iteration 3)
    (println "Goodbye buddy")
    (recur (inc iteration))))
    (defn recursive-printer
      ([]
         (recursive-printer 0))
      ([iteration]
         (println iteration)
         (if (> iteration 3)
           (println "Goodbye!")
           (recursive-printer (inc iteration)))))
    (recursive-printer)


;check if regular expression matches
(re-find #"^left-" "left-eye")

(matching-part {:name "left-eye" :size 1})
(matching-part {:name "head" :size 3})

(reduce + [1 2 3 4])
(reduce + 15 [1 2 3 4])

(defn my-reduce
  ([f initial coll]
   (loop [result initial
          remaining coll]
     (if (empty? remaining)
       result
       (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))

(defn better-symmetrize-body-parts
"Expects a seq of maps that have a :name and :size"
[asym-body-parts]
(reduce (fn [final-body-parts part]
          (into final-body-parts (set [part (matching-part part)])))
        []
        asym-body-parts))


(defn hit
  [asym-body-parts]
  (let [sym-parts (➊better-symmetrize-body-parts asym-body-parts)
        ➋body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    ➌(loop [[part & remaining] sym-parts
            accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))
