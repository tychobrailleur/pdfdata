(ns pdfdata.core
  (:require [pdfdata.pdfbox :as pdfbox]
            [pdfdata.paper :as paper])
  (:gen-class))

(defn format-dimensions [width height]
  (let [format (paper/paper-format width height)
        dimension-display (str width " x " height)]
    (if format
      (str dimension-display " (" format ")")
      dimension-display)))

(defn format-date [date]
  (-> (java.text.SimpleDateFormat. "dd/MM/yyyy HH:mm:ss")
      (.format (.getTime date))))

(defn -main [& args]
  (let [document (pdfbox/load-pdf (first args))
        info (pdfbox/get-doc-info document)]
    (println (format "Title: \t\t%s" (info :title)))
    (println (format "Keywords: \t%s" (info :keywords)))
    (println (format "Author: \t%s" (info :author)))
    (println (format "Creation Date: \t%s" (format-date (info :creation-date))))
    (println (format "Modif Date: \t%s" (format-date (info :modification-date))))
    (println (format "Number of Pages: \t%d" (info :pages)))
    (println (format "Page Size: \t%s" (format-dimensions (info :width) (info :height))))))
