<script>
import axios from "axios";

export default {
  name: "Home",
  data() {
   return{
      waiting: false,
   } 
  },
  methods: {
    sendChoice() {
      this.waiting = true;
      axios({
        method: 'get',
        url: 'http://localhost:4567/video/generate/random',
      }).then(() => {
        this.waiting = false;
        this.$router.push({ path: 'result' })
      });
    }
  }
};
</script>

<template>
  <div class="has-text-centered">
    <br/>
    <h1>Bienvenue sur Videogen</h1>
    <h4>Par R.Boitard et H.Palla</h4>
    <button @click="sendChoice" :class="waiting? 'button is-alert is-loading': 'button is-alert'" >Mode Aléatoire</button>&nbsp;
    <router-link to="/generate"><button class="button is-info">Mode Configurateur</button></router-link>
  </div>
</template>
