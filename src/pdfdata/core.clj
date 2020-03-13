(ns pdfdata.core
  (:require [pdfdata.pdfbox :as pdfbox])
  (:gen-class))

(defn format-dimensions [width height]
  (str width " x " height))

(defn -main [& args]
  (let [document (pdfbox/load-pdf (first args))
        info (pdfbox/get-doc-info document)]
    (prn info)))
