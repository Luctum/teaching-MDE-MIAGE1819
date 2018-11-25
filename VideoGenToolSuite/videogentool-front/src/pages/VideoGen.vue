<script>
import axios from "axios";
import VideoList from "../components/VideoList";
import Router from "vue-router";

export default {
  name: "VideoGen",
  components: {
    VideoList
  },
  data() {
    return {
      allVideos: ""
    };
  },
  created() {
    axios({ method: 'GET', url: 'http://localhost:4567/video/all', responseType:'application/json' }).then((response) => {
      this.allVideos = response.data;
    });
  },
  methods: {
    getAllVideos() {
      return this.allVideos;
    },
    sendChoice() {
      let choices = [];
      let elems = document.getElementById("choice").elements;
      for(let i = 0; i < elems.length; i++) {
        if(elems[i].checked && elems[i].value !== ''){
          choices.push(elems[i].value);
        }
      }
      axios({
        method: 'post',
        url: 'http://localhost:4567/video/generate',
        data: choices
      }).then(() => {
        this.$router.push({ path: 'result' })
      });
    }
  }
};
</script>

<template>
  <div class="container">
    <h1 class="has-text-centered">Choix de vidéo</h1>
    <p class="has-text-centered">Choisissez les éléments permettant de constituer votre vidéo personnalisée</p>
    <form id="choice" >
      <VideoList v-on: v-for="video in allVideos" :key="video.id" :video="video" />
      <button @click.prevent="sendChoice()" class="button is-link">Generer</button>
    </form>
  </div>
</template>
