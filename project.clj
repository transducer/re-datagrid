(defproject re-datagrid "0.1.0-SNAPSHOT"
  :description "A datagrid component for re-frame apps, with sorting, editing, etc."
  :url "https://github.com/Kah0ona/re-datagrid.git"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]
                 [reagent "0.8.0-alpha1"]
                 [re-frame-utils "0.1.0-SNAPSHOT"]
                 [com.taoensso/timbre "4.10.0"]
                 [prismatic/schema "1.1.6"]
                 [fipp "0.6.10"] ;;pretty printer cljs
                 [com.andrewmcveigh/cljs-time "0.5.1"] ;date/time
                 [re-frame "0.10.1"]]

  :plugins [[lein-cljsbuild "1.1.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target" "test/js"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :aliases {"dev"   ["do" "clean"
                     ["pdo" ["figwheel" "dev"]]]
            "demo"  ["do" "clean"
                     ["pdo" ["figwheel" "demo"]]]
            "build" ["do" "clean"
                     ["cljsbuild" "once" "min"]]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.7"]
                   [figwheel-sidecar "0.5.13"]
                   [com.cemerick/piggieback "0.2.2"]]

    :plugins [[lein-figwheel "0.5.13"]
              [lein-doo "0.1.8"]
              [lein-pdo "0.1.1"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "re-datagrid-demo.core/mount-root"}
     :compiler     {:main                 re-datagrid.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :closure-defines      {goog.DEBUG true}
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            re-datagrid.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :pseudo-names    false
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "demo"
     :source-paths ["src/cljs"]
     :compiler     {:main            re-datagrid-demo.core
                    :output-to       "resources/public/js/compiled/app-demo.js"
                    :output-dir      "resources/public/js/compiled/demo/out"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pseudo-names    false
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          re-datagrid.runner
                    :output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/test/out"
                    :optimizations :none}}]})
