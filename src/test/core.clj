(ns test.core
  (:gen-class))


; Exercise 1.1. 
;Below is a sequence of expressions. 
; What is the result printed by the interpreter in response to each expression? 
; Assume that the sequence is to be evaluated in the order in which it is presented.
          ; user=> 10
          ; 10
          ; user=> (+ 5 3 5)
          ; 13
          ; user=> (- 9 1)
          ; 8
          ; user=> (/ 6 2)
          ; 3
          ; user=> (+ (* 2 4) (- 4 6))
          ; 6
          ; user=> (def a 3)
          ; #'user/a
          ; user=> (def b (+ a 1))
          ; #'user/b
          ; user=> (+ a b (* a b))
          ; 19
          ; user=> (= a b)
          ; false
          ; user=> (if (and (> b a) (< b (* a b)))
          ;   #_=> b
          ;   #_=>     a)
          ; 4
          ; user=> (cond (= a 4) 6
          ;   #_=>        #_=> (= b 4) (+ 6 7 a)
          ;   #_=>        #_=> :else 25)
          ; 16
          ; user=> (+ 2 (if (> b a) b a))
          ; 6
          ; user=> (* (cond (> a b) a
          ;   #_=>        #_=> (< a b) b
          ;   #_=>        #_=> :else -1)
          ;   #_=>        #_=> (+ a 1))
          ; 16 



; Exercise 1.2. 
; Translate the following expression into prefix form
        ;  user=> (/ (+ 5 (+ 4 (- 2 (- 3 (+ 6 (/ 4 5)))))) (* 3 (- 6 2)(- 2 7)))
        ; -37/150


; Exercise 1.3. 
; Define a procedure that takes three numbers as arguments and returns the sum of the squares of the two larger numbers.

 (defn square
  [x]
  (* x x))

  (defn my-square
  [x]
  (* x x))

 (defn sum-of-squares
  [x y]
  (+ (square x) (square y))
 )

 (defn sumOfSquaresOf2Max
  [x y z]
  (cond 
    (= (min x y z) x) (sum-of-squares y z)
    (= (min x y z) y) (sum-of-squares x z)
    :else             (sum-of-squares x y)    
  )
)



 ; Exercise 1.4. 
 ; Observe that our model of evaluation allows for combinations whose operators are compound expressions. 
 ; Use this observation to describe the behavior of the following procedure:
 ; (define (a-plus-abs-b a b)
 ;   ((if (> b 0) + -) a b))
        ; The name of the procedure is  'a-plus-abs-b'
        ;   It takes 2 arguments - a and b
        ;   The if statement either returns ' +' (if b is a positive number) or '-' (if b is a negative number)
        ;   That gives us either (a + (+b)) or (a - (-b)), which is equal to a + |b|
        ;   Examples: 
        ;     (a-plus-abs-b 1 2)==>3
        ;     (a-plus-abs-b 1 -2)==>3
        ;     (a-plus-abs-b -1 2)==>-1
        ;     (a-plus-abs-b -1 -2)==>-1



; Exercise 1.5. 
; Ben Bitdiddle has invented a test to determine whether the interpreter he is faced with is using applicative-order evaluation or normal-order evaluation. 
; He defines the following two procedures:
; (define (p) (p))
; (define (test x y)
; (if (= x 0) 0
; y))

; Then he evaluates the expression
; (test 0 (p))

; What behavior will Ben observe with an interpreter that uses applicative-order evaluation? 
; What behavior will he observe with an interpreter that uses normal-order evaluation? 
; Explain your answer.
; (Assume that the evaluation rule for the special form if is the same whether the interpreter is using normal or applicative order: 
;   The predicate expression is evaluated first, and the result determines whether to evaluate the consequent or the alternative expression.)
          ; applicative-order evaluation:
          ;   (test 0 (p))
          ;   (if (= 0 0) 0
              ; p))
          ;this would be stuck in an infinite loop, since p can't be evaluated
          ; normal-order evaluation:
          ;   (test 0 (p))
          ;   (if (= 0 0) 0
          ;    p))
          ;since x = 0, this would return 0 and terminate. The else will not be evaluated.



; Section: 1.1.7 Example: Square Roots by Newton’s Method
(defn average
  [x y]
  (/ (+ x y) 2))

(defn improve 
  [guess x]
  (average guess (/ x guess)))

(defn good-enough? 
  [guess x]
  (< (Math/abs (- (square guess) x)) 0.001))

(defn sqrt-iter 
  [guess x]
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x)
                  x)))

(defn sqrt 
  [x]
  (sqrt-iter 1.0 x))

; Exercise 1.6. Alyssa P. Hacker doesn’t see why if needs to be provided as a special form. 
; ‘‘Why can’t I just define it as an ordinary procedure in terms of cond?’’ she asks. 
; Alyssa’s friend Eva Lu Ator claims this can indeed be done, and she defines a new version of if:
; (define (new-if predicate then-clause else-clause)
;   (cond (predicate then-clause)
;         (else else-clause)))

; Eva demonstrates the program for Alyssa:
; (new-if (= 2 3) 0 5)
; 5
; (new-if (= 1 1) 0 5)
; 0

; Delighted, Alyssa uses new-if to rewrite the square-root program:
; (define (sqrt-iter guess x)
;   (new-if (good-enough? guess x)
;           guess
;           (sqrt-iter (improve guess x)
;                       x)))
; What happens when Alyssa attempts to use this to compute square roots? Explain.
          ; As in example 1.5, new-if will be stuck in a infinite loop since it uses applicative-order evaluation.
          ; Both the pieces (if and else) will have to be evaluated before the method is used
          ; This is not the case with if, where if the first condition is true, else will not be evaluated



; Exercise 1.7. 
; The good-enough? test used in computing square roots will not be very effective for finding the square roots of very small numbers. 
; Also, in real computers, arithmetic operations are almost always performed with limited precision. 
; This makes our test inadequate for very large numbers. 
; Explain these statements, with examples showing how the test fails for small and large numbers. 
; An alternative strategy for implementing good-enough? is to watch how guess changes from one iteration to the next 
;   and to stop when the change is a very small fraction of the guess. Design a square-root procedure that uses this kind of end test. 
; Does this work better for small and large numbers?

          ; The predetermined tolerance for good-enough? is set to 0.001. This is too high for very small or very large numbers
          ; Example: test for very small number 0.010
          ; FAIL in (sqrt-test) (core_test.clj:38)
          ; Test for sqrt function.
          ; expected: (= "0.010" (format "%.3f" (sqrt 1.0E-4)))
          ;   actual: (not (= "0.010" "0.032"))

          ; Example: test for very small number 10000000000000
          ; ERROR in (sqrt-test) (HashMap.java:339)
          ; Test for sqrt function.
          ; expected: (= "3162433.547" (format "%.3f" (sqrt 10000000000000)))
          ;   actual: java.lang.StackOverflowError: null


          ;stop when the change is a very small fraction of the guess
          (defn modified-good-enough? 
            [currentguess newguess]
            (< (Math/abs (- currentguess newguess)) (* currentguess 0.001)))

        (defn modified-sqrt-iter 
          [currentguess newguess x]
          (if (modified-good-enough? currentguess newguess)
          currentguess
          (modified-sqrt-iter newguess (improve newguess x)
                      x)))

        (defn modified-sqrt 
          [x]
          (modified-sqrt-iter 1.0 2.0 x))



; Exercise 1.8. 
; Newton’s method for cube roots is based on the fact that if y is an approximation to the cube root of x, 
; then a better approximation is given by the value

  ; (/ 
  ;   (
  ;     (+ 
  ;       (/
  ;         x
  ;         square y) 
  ;       (* 2 y))
  ;   ) 
  ;   3
  ; )


; Use this formula to implement a cube-root procedure analogous to the square-root procedure. 
; (In section 1.3.4 we will see how to implement Newton’s method in general as an abstraction of these square-root and cube-root procedures.)

(defn improve-cube-root 
  [guess x]
    (/ (+ (/ x (square guess)) (* 2 guess)) 3)
)    

(defn cube-root-iter  
  [currentguess newguess x]
  (if (modified-good-enough? currentguess newguess)
      currentguess
      (cube-root-iter newguess (improve-cube-root newguess x)
                      x)))

(defn cube-root 
  [x]
  (cube-root-iter 1.0 2.0 x))

 ; (defn goodbye
	; [name]
	; (str "Goodbye " name))


;(is (= [] (max-n '() 0))
 ; (defn max-n
 ;  [coll n]
 ;  ;exit condition
 ;  (if (or (empty? coll) (= n 0))
 ;    []
 ;    (conj (max-n coll (- n 1)) (max coll))  ;reduce the coll by the max
 ;  )
 ; )



; Exercise 1.9. 
; Each of the following two procedures defines a method for adding two positive integers 
; in terms of the procedures inc, which increments its argument by 1, 
; and dec, which decrements its argument by 1.
;
; (define (+ a b)
;   (if (= a 0)
;        b
;       (inc (+ (dec a) b))))
;
; (define (+ a b)
;   (if (= a 0) 
;        b
;        (+ (dec a) (inc b))))
;
; Using the substitution model, illustrate the process generated by each procedure in 
; evaluating (+ 4 5). Are these processes iterative or recursive?

          ; (+ 4 5)
          ; (inc (+ (dec 4) 5))
          ; (inc (+ (3) 5))
          ; (inc (inc (+ (dec 3) 5)))
          ; (inc (inc (+ (2) 5)))
          ; (inc (inc (inc (+ (dec 2) 5))))
          ; (inc (inc (inc (+ (1) 5))))
          ; (inc (inc (inc (inc (+ (dec 1) 5)))))
          ; (inc (inc (inc (inc (+ (0) 5)))))
          ; (inc (inc (inc (inc 5))))
          ; (inc (inc (inc (6))))
          ; (inc (inc (7)))
          ; (inc (8))
          ; 9

          ; ==> linear recursive process 


          ; (+ 4 5)
          ; (+ (dec 4) (inc 5))
          ; (+ 3 6)
          ; (+ (dec 3) (inc 6))
          ; (+ 2 7)
          ; (+ (dec 2) (inc 7))
          ; (+ 1 8)
          ; (+ (dec 1) (inc 8))
          ; (+ 0 9)
          ; 9

          ; ==> linear iterative process 



; Exercise 1.10. 
; The following procedure computes a mathematical function called Ackermann’s function.

; (define (A x y)
;   (cond ((= y 0) 0)
;         ((= x 0) (* 2 y))
;         ((= y 1) 2)
;         (else (A (- x 1)
;                  (A x (- y 1))))))

; What are the values of the following expressions?
; (A 1 10)
; (A 2 4)
; (A 3 3)

; Consider the following procedures, where A is the procedure defined above:
; (define (f n) (A 0 n))
; (define (g n) (A 1 n))
; (define (h n) (A 2 n))
; (define (k n) (* 5 n n))
; Give concise mathematical definitions for the functions computed by the procedures f, g, and h 
; for positive integer values of n. For example, (k n) computes 5n 2 .

          ; (A 1 10)
          ; (A 0 (A 1 9))
          ; (A 0 (A 0 (A 1 8)))
          ; (A 0 (A 0 (A 0 (A 1 7))))
          ; (A 0 (A 0 (A 0 (A 0 (A 1 6)))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 32)))))
          ; (A 0 (A 0 (A 0 (A 0 64))))
          ; (A 0 (A 0 (A 0 128)))  
          ; (A 0 (A 0 256))  
          ; (A 0 512)
          ; 1024    


          ; (A 2 4)
          ; (A 1 (A 2 3))
          ; (A 1 (A 1 (A 2 2)))
          ; (A 1 (A 1 (A 1 (A 2 1))))
          ; (A 1 (A 1 (A 1 2)))
          ; (A 1 (A 1 (A 0 (A 1 1))))
          ; (A 1 (A 1 (A 0 2)))
          ; (A 1 (A 1 4))
          ; (A 1 (A 0 (A 1 3)))
          ; (A 1 (A 0 (A 0 (A 1 2))))
          ; (A 1 (A 0 (A 0 (A 0 (A 1 1)))))
          ; (A 1 (A 0 (A 0 (A 0 2))))
          ; (A 1 (A 0 (A 0 4)))
          ; (A 1 (A 0 8))
          ; (A 1 16)
          ; (A 0 (A 1 15))
          ; (A 0 (A 0 (A 1 14)))
          ; (A 0 (A 0 (A 0 (A 1 13))))
          ; (A 0 (A 0 (A 0 (A 0 (A 1 12)))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 11))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 10)))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 9))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 8)))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 7))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 6)))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 32)))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 64))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 128)))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 256))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 512)))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 1024))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 2048)))))
          ; (A 0 (A 0 (A 0 (A 0 4096))))
          ; (A 0 (A 0 (A 0 8192))
          ; (A 0 (A 0 16384))
          ; (A 0 32768)
          ; 65536  


          ; (A 3 3)
          ; (A 2 (A 3 2))
          ; (A 2 (A 2 (A 3 1)))
          ; (A 2 (A 2 2))
          ; (A 2 (A 1 (A 2 1)))
          ; (A 2 (A 1 2))
          ; (A 2 (A 0 (A 1 1)))
          ; (A 2 (A 0 2))
          ; (A 2 4)
          ; (A 1 (A 2 3))
          ; (A 1 (A 1 (A 2 2)))
          ; (A 1 (A 1 (A 1 (A 2 1))))
          ; (A 1 (A 1 (A 1 2)))
          ; (A 1 (A 1 (A 0 (A 1 1))))
          ; (A 1 (A 1 (A 0 2)))
          ; (A 1 (A 1 4))
          ; (A 1 (A 0 (A 1 3)))
          ; (A 1 (A 0 (A 0 (A 1 2))))
          ; (A 1 (A 0 (A 0 (A 0 (A 1 1)))))
          ; (A 1 (A 0 (A 0 (A 0 2))))
          ; (A 1 (A 0 (A 0 4)))
          ; (A 1 (A 0 8))
          ; (A 1 16)
          ; (A 0 (A 1 15))
          ; (A 0 (A 0 (A 1 14)))
          ; (A 0 (A 0 (A 0 (A 1 13))))
          ; (A 0 (A 0 (A 0 (A 0 (A 1 12)))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 11))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 10)))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 9))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 8)))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 7))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 6)))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 5))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 4)))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 3))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 2)))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 1 1))))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 2)))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 4))))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 8)))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 16))))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 32)))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 64))))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 128)))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 256))))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 512)))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 (A 0 1024))))))
          ; (A 0 (A 0 (A 0 (A 0 (A 0 2048)))))
          ; (A 0 (A 0 (A 0 (A 0 4096))))
          ; (A 0 (A 0 (A 0 8192))
          ; (A 0 (A 0 16384))
          ; (A 0 32768)
          ; 65536  


          ; (define (f n) (A 0 n)) ==> 2n
          ; (define (g n) (A 1 n)) ==> 2 to the power of n ==> 2 ^ n
          ; (define (h n) (A 2 n)) ==> 2 to the power of 2*2n ==> 2 ^ 2*2n



; Exercise 1.11. 
; A function f is defined by the rule that f(n) = n if n<3 and f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) if n> 3. 
; Write a procedure that computes f by means of a recursive process. 
; Write a procedure that computes f by means of an iterative process.

          ;f by means of a recursive process
          (defn f-recursive
            [n]
            (if (< n 3)
              n
              (+ (f-recursive(- n 1))
                 (* 2 (f-recursive(- n 2)))
                 (* 3 (f-recursive(- n 3))))))
         
          ; ;f by means of an iterative process
          (defn f-iter 
            [a b c count]
            (if (< count 3)
            a
            (f-iter (+ a (* 2 b) (* 3 c))
                    a
                    b
                    (- count 1)  
            )
            )
          )

          (defn f-iterative
            [n]
            (if (< n 3)
              n
              (f-iter 2 1 0 n)
            )  
          )

; f(0) = 0
; f(1) = 1
; f(2) = 2
; f(3) = f(2)+2f(1)+3f(0) = 2+2+0 = 4
; f(4) = f(3)+2f(2)+3f(1) = 4+4+3 = 11



;  Exercise 1.12. 
;  The following pattern of numbers is called Pascal’s triangle.
;         1
;       1  1
;     1  2  1 
;   1  3  3  1
; 1  4  6  4  1
;  The numbers at the edge of the triangle are all 1, and each number inside the triangle is the sum of the two numbers above it.
;  Write a procedure that computes elements of Pascal’s triangle by means of a recursive process.
    
          (defn pascal-recursive
            [row column]
            (if (> column row)
              "incorrect column number, element does not exist"
              (if (< row 3)
                1
                (if (or (= column 1) (= column row))
                  1
                  (+ (pascal-recursive (- row 1)(- column 1))
                    (pascal-recursive (- row 1) column))))))



; Exercise 1.13. 
; Prove that Fib(n) is the closest integer to φ^n / √5, where φ = (1 + √5)/2. 
; Hint: ψ = (1 - √5)/2. 
; Use induction and the definition of the Fibonacci numbers (see section 1.2.2) to prove that Fib(n) = (φ^n - ψ^n) / √5.       

          (defn phi
            []
            (/ (+ 1 (sqrt 5)) 2)) 

          (defn psi
            []
            (/ (- 1 (sqrt 5)) 2)) 

          (defn power-iter
            [x prod power]
            (if (= power 0)
                prod
                (power-iter x (* x prod) (- power 1))))


          (defn to-the-power-of
            [x power]
            (power-iter x 1 power))

          (defn fib1
            [n]
            (/ 
              (to-the-power-of (phi) n)
              (sqrt 5)))

          (defn fib2
            [n]
            (/ 
              (- 
                (to-the-power-of (phi) n)
                (to-the-power-of (psi) n)) 
              (sqrt 5)))




(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;(println "Hello, World!")
  ;(println (goodbye "Alex"))
  (println (sumOfSquaresOf2Max 3 1 2)))
