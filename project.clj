(defproject pdfdata "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "GNU General Public License v3.0"
            :url "none"
            :year 2020
            :key "gpl-3.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.apache.pdfbox/pdfbox "2.0.19"]]
  :repl-options {:init-ns pdfdata.core}
  :main pdfdata.core)
