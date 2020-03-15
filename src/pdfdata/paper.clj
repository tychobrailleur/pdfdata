(ns pdfdata.paper)

(defn round
  [d]
  (Math/round d))

(defn abs [n]
  (Math/abs n))

(defn floor [n]
  (int (Math/floor n)))

(defn fourth-root [n]
  (Math/sqrt (Math/sqrt n)))

(defn square-root [n]
  (Math/sqrt n))

(defn pt->mm [length]
  (* length 0.3528))

(def alpha-a (fourth-root 2))

;; These are the theoretical implementations
;; but they don't return the correct results due to rounding.

;; (defn iso-aseries-size [size]
;;   {:size (str "A" size)
;;    :width (* alpha-a (Math/pow 2 (- (/ (inc size) 2))) 1000.0)
;;    :height (* alpha-a (Math/pow 2 (- (/ size 2))) 1000.0)})

;; (defn iso-aseries-size [size]
;;   (if (even? size)
;;     {:size (str "A" size)
;;      :width (/ 1000.0 (* (Math/pow 2.0 (/ size 2)) (fourth-root 2)))
;;      :height (/ (* 1000.0 (fourth-root 2)) (Math/pow 2 (/ size 2)))}
;;     {:size (str "A" size)
;;      :width (/ (* 1000.0 (fourth-root 2)) (Math/pow 2 (/ (inc size) 2)))
;;      :height (/ 1000.0 (* (Math/pow 2.0 (/ (dec size) 2)) (fourth-root 2)))}))


(defn iso-aseries-size [size]
  (if (= size 0)
    {:size (str "A" size)
     :height (round (* 1000.0 alpha-a))
     :width (round (* 1000.0 (/ 1.0 alpha-a)))}
    (let [prev (iso-aseries-size (dec size))
          area (* (:width prev) (:height prev))
          new-area (floor (/ area 2.0))]
      {:size (str "A" size)
       :height (:width prev)
       :width (floor (/ new-area (:width prev)))})))

(defn iso-paper-format [width height]
  (->> (range 11)
       (map #(iso-aseries-size %))
       (filter #(and (< (abs (- (min width height) (:width %))) 1)
                     (< (abs (- (max width height) (:height %))) 1)))
       first
       :size))


(defn paper-format
  "Returns the paper format based on the `width` and `height` in pts.

  Cf. https://papersizes.io/us/ for US paper sizes"
  [width height]
  (cond
    (and (< (abs (- (min width height) 397)) 0.5)
         (< (abs (- (max width height) 612)) 0.5)) "US half letter"
    (and (< (abs (- (min width height) 360)) 0.5)
         (< (abs (- (max width height) 576)) 0.5)) "US junior legal"
    (and (< (abs (- (min width height) 612)) 0.5)
         (< (abs (- (max width height) 792)) 0.5)) "US letter"
    (and (< (abs (- (min width height) 612)) 0.5)
         (< (abs (- (max width height) 1009)) 0.5)) "US legal"
    :else (iso-paper-format (pt->mm width) (pt->mm height))))
