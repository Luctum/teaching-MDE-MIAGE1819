<script>
import axios from "axios";
import Base64Img from "../components/Base64Img"

export default {
  name: "VideoGen",
  components: {
    Base64Img,
  },
  data() {
    return{
      allVideos: ''
    }
    
  },
  props: {
  },
  created() {
    axios({method: 'GET', url: 'http://localhost:4567/video/all', responseType:'application/json'}).then((response) => {
        this.allVideos = response.data;
    })
  }
};
</script>

<template>
  <div>
    <ul>
      <li v-for="video in allVideos" :key="video.id">
        {{video.id}} {{video.name}} {{video.type}}
        <Base64Img v-if="!video.videos" :image="video.image" />
        <ul v-if="video.videos">
          <li  v-for="alternative in video.videos" :key="alternative.id">
            {{alternative.id}} {{alternative.name}} {{alternative.type}}
            <Base64Img :image="alternative.image" />
          </li>
        </ul>
      </li>
    </ul>
  </div>
</template>
