(defn initiate [min max]
  (def ^:dynamic min-bound min)
  (def ^:dynamic max-bound max)
  (def ^:dynamic chosen-number (rand-int (inc max)))
  (println "\nNumber chosen. Let's begin the guessing game.\n"))

(def current-attempt (atom 0))

(defn attempt-guess []
  (let [guess (rand-int (+ min-bound max-bound))]
    (reset! current-attempt guess)
    (println "\nIs it this number? " guess "\n")))

(defn hint-lower []
  (let [decrement-by (rand-int 6)
        new-attempt (- @current-attempt (inc decrement-by))]
    (if (= new-attempt chosen-number)
      (println "\nCorrect guess! The number was: " new-attempt)
      (do
        (alter-var-root #'max-bound (constantly new-attempt))
        (println "\nToo high. New guess: " new-attempt "\n")
        (reset! current-attempt new-attempt)))))

(defn hint-higher []
  (let [increment-by (rand-int 6)
        new-attempt (+ @current-attempt (inc increment-by))]
    (if (= new-attempt chosen-number)
      (println "\nCorrect guess! The number was: " new-attempt)
      (do
        (alter-var-root #'min-bound (constantly new-attempt))
        (println "\nToo low. New guess: " new-attempt "\n")
        (reset! current-attempt new-attempt)))))