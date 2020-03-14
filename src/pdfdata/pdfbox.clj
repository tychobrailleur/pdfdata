(ns pdfdata.pdfbox
  (:import [org.apache.pdfbox.pdmodel PDDocument]
           [java.io File]))

(defn load-pdf [path]
  (PDDocument/load (File. path)))

(defn get-doc-info [doc]
  (let [info (.getDocumentInformation doc)
        page (.getPage doc 0)
        height (-> page
                   .getMediaBox
                   .getHeight)
        width (-> page
                  .getMediaBox
                  .getWidth)]
    {:title (.getTitle info)
     :author (.getAuthor info)
     :creator (.getCreator info)
     :keywords (.getKeywords info)
     :creation-date (.getCreationDate info)
     :modification-date (.getModificationDate info)
     :producer (.getProducer info)
     :pages (.getNumberOfPages doc)
     :width width
     :height height}))
