<script>
import axios from "axios";

export default {
  name: "Result",
  data () {
    return {
      waiting: false,
      gif: undefined
    }
  },
  methods: {
    "generateGif": function(){
      this.waiting = true;
      axios({method: 'GET', url: 'http://localhost:4567/gif', responseType:'image/gif'}).then((response) => {
        
        this.waiting = false;
        this.gif = response.data

        const image = "data:image/png;base64," + this.gif;
        //Transform the image into a blob to be downloaded
        fetch(image)
        .then(res => res.blob())
        .then(blob => {
          const url = window.URL.createObjectURL(new Blob([blob]));
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', 'output.gif');
          document.body.appendChild(link);
          link.click();
        });
        
        
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
                  <h3 v-if=waiting >Veuillez patienter pendant la génération du gif...</h3>
                  <img v-if='gif !== undefined && !waiting' :src="'data:image/png;base64,' + gif"/>
                </div>
                <button @click=generateGif :class="waiting? 'button is-primary is-loading' : 'button is-primary'">Genere un gif</button>
                <router-link to="/generate"><button class="button">Retour</button></router-link>
              </div>
          </div>
      </div>
      <div class="column">
      </div>
    </div>
  </div>
</template>
