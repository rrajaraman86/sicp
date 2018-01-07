(ns test.core-test
  (:require [clojure.test :refer :all]
            [test.core :refer :all]))

; (deftest a-test
;   (testing "FIXME, I fail."
;     (is (= 0 1))))

; (deftest goodbye-test
;   (testing "Test for goodbye function."
;     (is (= "Goodbye Alex" (goodbye "Alex")))))

(deftest square-test
  (testing "Test for square function."
    (is (= 4 (square 2)))
    (is (= 4 (square -2)))
    (is (= 0 (square 0)))
    )
)

(deftest sumOfSquaresOf2Max-test
  (testing "Test for sumOfSquaresOf2Max function."
    (is (= 13 (sumOfSquaresOf2Max 1 2 3)))
    (is (= 5 (sumOfSquaresOf2Max 1 2 -3)))
    (is (= 5 (sumOfSquaresOf2Max 1 2 0)))
    (is (= 5 (sumOfSquaresOf2Max 1 -2 -3)))
    (is (= 5 (sumOfSquaresOf2Max -1 -2 -3)))
    )
)

(deftest sqrt-test
  (testing "Test for sqrt function."
    (is (= "1.000" (format "%.3f"(sqrt 1))))
    (is (= "1.414" (format "%.3f"(sqrt 2))))
    (is (= "2.000" (format "%.3f"(sqrt 4))))
    (is (= "3.000" (format "%.3f"(sqrt 9))))
    ;test for very small number
    ;(is (= "0.010" (format "%.3f"(sqrt 0.0001))))
    ;test for very large number
    ;(is (= "3162433.547" (format "%.3f"(sqrt 10000000000000))))  
    )
)

(deftest modified-sqrt-test
  (testing "Test for modified-sqrt function."
    (is (= "1.000" (format "%.3f"(modified-sqrt 1))))
    (is (= "1.414" (format "%.3f"(modified-sqrt 2))))
    (is (= "2.000" (format "%.3f"(modified-sqrt 4))))
    (is (= "3.000" (format "%.3f"(modified-sqrt 9))))
    ;test for very small number
    (is (= "0.010" (format "%.3f"(modified-sqrt 0.0001))))
    ;test for very large number
    (is (= "3162433.547" (format "%.3f"(modified-sqrt 10000000000000))))  
    )
)

(deftest cube-root-test
  (testing "Test for cube-root function."
    (is (= "1.000" (format "%.3f"(cube-root 1))))
    (is (= "2.000" (format "%.3f"(cube-root 8))))
    (is (= "2.926" (format "%.3f"(cube-root 25))))
    (is (= "3.003" (format "%.3f"(cube-root 27)))) 
    )
)

; (deftest max-n-of-coll-test
;   (testing "Test for max-n-of-coll function."
;     (is (= [] (max-n '() 0))
;     ; (is (= [] (max-n '(5) 0))
;     ; (is (= [5] (max-n '(5) 1))
;     ; (is (= [7] (max-n '(5 7) 1))
;     ; (is (= [5 7] (max-n '(5 7) 2))
;     )
; )

