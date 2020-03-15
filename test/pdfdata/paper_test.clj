(ns pdfdata.paper-test
  (:require [pdfdata.paper :as sut]
            [clojure.test :as t]))

(def a-sizes
  [{:size "A0" :width 841 :height 1189}
   {:size "A1" :width 594 :height 841}
   {:size "A2" :width 420 :height 594}
   {:size "A3" :width 297 :height 420}
   {:size "A4" :width 210 :height 297}
   {:size "A5" :width 148 :height 210}
   {:size "A6" :width 105 :height 148}
   {:size "A7" :width 74 :height 105}
   {:size "A8" :width 52 :height 74}
   {:size "A9" :width 37 :height 52}
   {:size "A10" :width 26 :height 37}])

(def us-sizes
  [{:size "US half letter" :width 397 :height 612}
   {:size "US junior legal" :width 360 :height 576}
   {:size "US letter" :width 612 :height 792}
   {:size "US legal" :width 612 :height 1009}])

(t/deftest test-iso-size
  (t/testing "A series"
    (t/is (every? #(= (sut/iso-aseries-size %) (nth a-sizes %)) (range 11)))))

(t/deftest test-us-size
  (t/testing "US sizes"
    (t/is (every? #(= (sut/paper-format
                       (:width (nth us-sizes %))
                       (:height (nth us-sizes %)))
                      (:size (nth us-sizes %)))
                  (range 4)))))

(t/deftest test-paper-format
  (t/testing "A series"
    (t/is (= "A4" (sut/paper-format 595 842))))
  (t/testing "US"
    (t/is (= "US half letter" (sut/paper-format 612 397)))))
