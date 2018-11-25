<script>
import axios from "axios";
import Vue from 'vue'

export default {
  name: "Result",
  props: {
  },
  methods: {
    "generateGif": function(){
      axios({method: 'GET', url: 'http://localhost:4567/gif', responseType:'image/gif'}).then((response) => {
        console.log("ok");
        return response.data;
      })
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
                  <!-- <img src="http://localhost:4567/gif"/> -->
                </div>
              </div>
          </div>
      </div>
      <div class="column">
      </div>
    </div>
  </div>
</template>
