<script>
import axios from "axios";
import VideoList from "../components/VideoList";

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
    }
  }
};
</script>

<template>
  <div class="container">
    <h1 class="has-text-centered">Choix de vidéo</h1>
    <p class="has-text-centered">Choisissez les éléments permettant de constituer votre vidéo personnalisée</p>
    <form>
      <VideoList v-for="video in allVideos" :key="video.id" :video="video" />
      <button class="button is-link">Generer</button>
    </form>
  </div>
</template>
