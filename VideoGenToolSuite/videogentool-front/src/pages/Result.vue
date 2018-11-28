<script>
import axios from "axios";

export default {
  name: "Result",
  data () {
    return {
      waiting: false
    }
  },
  methods: {
    "generateGif": function(){
      this.waiting = true;
      axios({method: 'GET', url: 'http://localhost:4567/gif', responseType:'image/gif'}).then((response) => {
        console.log("ok");
        this.waiting = false;
        return response;
      });
    }
  }
};
</script>

<template>
  <div>
    <div class="result has-text-centered">
      <h1 class="is-large">Vidéo Generée</h1>
    </div>
    <div class="columns">
      <div class="column">
      </div>
      <div class="column">
          <div class="card">
              <div class="card-content">
                <div class="media">
                  <video controls src="http://localhost:4567/video"></video>
                </div>
                <div class="media">
                  <h3 v-if=waiting >Veuillez patienter pendant la génération du gif</h3>
                  <!-- <img src="http://localhost:4567/gif"/> -->
                  <button @click=generateGif class="button is-primary">Genere un gif</button>
                  <router-link to="/generate"><button class="button">Retour</button></router-link>
                </div>
              </div>
          </div>
      </div>
      <div class="column">
      </div>
    </div>
  </div>
</template>
