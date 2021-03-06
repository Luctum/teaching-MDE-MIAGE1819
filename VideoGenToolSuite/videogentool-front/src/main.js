import Vue from "vue";
import App from "./App.vue";
import Router from "vue-router";
import Result from "@/pages/Result";
import Home from "@/pages/Home";
import VideoGen from "@/pages/VideoGen";

Vue.config.productionTip = false;

Vue.use(Router);

let router = new Router({
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    {
      path: "/generate",
      name: "generer",
      component: VideoGen
    },
    {
      path: "/result",
      name: "resultat",
      component: Result
    }
  ]
});

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
