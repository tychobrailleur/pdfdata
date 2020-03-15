(ns pdfdata.pdfbox-test
  (:require [pdfdata.pdfbox :as sut]
            [clojure.test :as t]))


(def doc (sut/load-pdf "./resources/test.pdf"))

(t/deftest test-get-doc-info
  (t/testing "PDF info"
    (t/is (= (:title (sut/get-doc-info doc)) "Test"))
    (t/is (= (:author (sut/get-doc-info doc)) "Sébastien Le Callonnec"))
    (t/is (= (:creator (sut/get-doc-info doc)) "LaTeX with hyperref"))
    (t/is (= (:producer (sut/get-doc-info doc)) "pdfTeX-1.40.21"))
    (t/is (= (:pages (sut/get-doc-info doc)) 1))
    (t/is (= (int (:height (sut/get-doc-info doc))) 841))
    (t/is (= (int (:width (sut/get-doc-info doc))) 595))))

(.close doc)
