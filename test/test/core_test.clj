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

(deftest f-recursive-test
  (testing "Test for f-recursive function."
    (is (= -1 (f-recursive -1)))
    (is (= 0 (f-recursive 0)))
    (is (= 1 (f-recursive 1)))
    (is (= 2 (f-recursive 2)))
    (is (= 4 (f-recursive 3)))
    (is (= 11 (f-recursive 4)))
    )
)

(deftest f-iterative-test
  (testing "Test for f-iterative-test function."
    (is (= -1 (f-iterative -1)))
    (is (= 0 (f-iterative 0)))
    (is (= 1 (f-iterative 1)))
    (is (= 2 (f-iterative 2)))
    (is (= 4 (f-iterative 3)))
    (is (= 11 (f-iterative 4)))
    )
)

(deftest pascal-recursive-test
  (testing "Test for pascal-recursive function."
    (is (= 1 (pascal-recursive 1 1)))
    (is (= 1 (pascal-recursive 2 1)))
    (is (= 1 (pascal-recursive 2 2)))
    (is (= 1 (pascal-recursive 3 1)))
    (is (= 2 (pascal-recursive 3 2)))
    (is (= 1 (pascal-recursive 3 3)))
    (is (= 1 (pascal-recursive 4 1)))
    (is (= 3 (pascal-recursive 4 2)))
    (is (= 3 (pascal-recursive 4 3)))
    (is (= 1 (pascal-recursive 4 4)))
    (is (= 1 (pascal-recursive 5 1)))
    (is (= 4 (pascal-recursive 5 2)))
    (is (= 6 (pascal-recursive 5 3)))
    (is (= 4 (pascal-recursive 5 4)))
    (is (= 1 (pascal-recursive 5 5)))
    (is (= 1 (pascal-recursive 15 1)))
    (is (= 2002 (pascal-recursive 15 10)))
    (is (= 1 (pascal-recursive 15 15)))
    (is (= "incorrect column number, element does not exist" (pascal-recursive 5 6)))
    )
)

(deftest to-the-power-of-test
  (testing "Test for to-the-power-of function."
    (is (= 1 (to-the-power-of 2 0)))
    (is (= 2 (to-the-power-of 2 1)))
    (is (= 32 (to-the-power-of 2 5)))
    )
)

(deftest fib1-test
  (testing "Test for fib1 function."
    (is (= "0.447" (format "%.3f"(fib1 0))))
    (is (= "0.724" (format "%.3f"(fib1 1))))
    (is (= "4.960" (format "%.3f"(fib1 5))))
    (is (= "6765.036" (format "%.3f"(fib1 20))))
    )
)

(deftest fib2-test
  (testing "Test for fib2 function."
    (is (= "0.000" (format "%.3f"(fib2 0))))
    (is (= "1.000" (format "%.3f"(fib2 1))))
    (is (= "5.000" (format "%.3f"(fib2 5))))
    (is (= "6765.036" (format "%.3f"(fib2 20))))
    )
)










