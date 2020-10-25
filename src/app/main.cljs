(ns app.main
  (:require [reagent.dom :as dom]
            [reagent.core :as r]))

(def click-count (r/atom 0))

;; example 1, counter
(defn counting-component []
  [:div
   [:h2 "Counter"]
   [:button {:style {:cursor "pointer"}
             :on-click #(swap! click-count inc)}
    "times clicked: "
    [:span {:style {:color "dodgerblue"
                    :font-size "18px"}} @click-count]]])

;; example 2, timer
(defn timer-component []
  (let [seconds-elapsed (r/atom 0)]
    (fn []
      (js/setTimeout #(swap! seconds-elapsed inc) 1000)
      [:div {:style {:margin-top "20px"}}
       [:h2 "Timer"]
       "Seconds Elapsed: " @seconds-elapsed])))

;; exmple 3, form
(defn atom-input [value]
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))}])

(defn form-component []
  (let [val (r/atom "foo")]
    (fn []
      [:div
       [:h2 "Input Form"]
       [:p "The value is now: " @val]
       [:p "Change it here: " [atom-input val]]])))


;; main app
(defn app []
  [:div {:class "wrapper"}
   [:h1 "Hello there," [:br] "this is React with CLJS"]
   [:div {:class "counting"} [counting-component]]
   [:div {:class "timer"} [timer-component]]
   [:div {:class "form"} [form-component]]])

;; renderer
(defn render-app []
  (dom/render [app] (.getElementById js/document "root")))

(render-app)



(defn main! []
  (println "Started!"))
(defn reload! []
  (main!)
  (println "Reloaded!"))

