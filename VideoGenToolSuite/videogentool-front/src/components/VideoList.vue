<script>
import Video from "../components/Video";
import Base64Img from "../components/Base64Img";
export default {
  name: "VideoList",
  components: {
    Video,
    Base64Img
  },
  props: {
    video: null
  }
};
</script>


<template>
  <div class="tile is-parent">
    <div class="tile box is-vertical is-warning">
      <!-- Video title / subtitle-->
      <h2 class="tags has-addons" v-if="video.type"><span class="tag is-large">{{video.type}}</span> <span class="tag is-warning is-large">{{video.id}}</span></h2>
      <h3 class="tag is-dark" v-else>
        {{video.id}}
        <!-- Alternative video input -->
        &nbsp;<input :name="video.parentId" value='video.id' type="radio" checked>
      </h3>
      <!-- Optional video inputs -->
      <div class="control" v-if="video.type === 'optional'">
        <p>Inclure cette vidéo ?</p>
        <label class="radio">Oui <input checked :name="video.id" :value="video.id" type="radio"></label>
        <label class="radio">Non <input :name="video.id" value='' type="radio"></label>
      </div>
      <!-- Image -->
      <Base64Img v-if="!video.videos" :image="video.image" />
      <!-- Recursive subvideos-->
      <div class="tile" v-if="video.videos">
        <VideoList v-for="subVideo in video.videos" :key="subVideo.id" :video="subVideo" />
      </div>
    </div>
  </div>
</template>