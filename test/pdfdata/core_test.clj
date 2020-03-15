(ns pdfdata.core-test
  (:require [clojure.test :refer :all]
            [pdfdata.core :refer :all]))

(deftest test-format-dimensions
  (testing "Displaying paper format if possible"
    (is (= "595 x 842 (A4)" (format-dimensions 595 842)))
    (is (= "594 x 774.1" (format-dimensions 594 774.1)))))

(deftest test-format-date
  (testing "Display date"
    (is (= "15/03/2020 21:00:00" (format-date (java.util.Date. 1584306000000))))))
