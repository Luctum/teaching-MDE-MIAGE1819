import Vue from "vue";
import App from "./App.vue";
import Router from "vue-router";
import Result from "@/pages/Result";
import VideoGen from "@/pages/VideoGen";

Vue.config.productionTip = false;

Vue.use(Router);

let router = new Router({
  routes: [
    {
      path: "/generate",
      name: "Generer votre video",
      component: VideoGen
    },
    {
      path: "/result",
      name: "Resultat",
      component: Result
    }
  ]
});

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
