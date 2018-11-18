<script>
import Result from "./pages/Result.vue";

require('bulma/css/bulma.css');

export default {
  name: "app",
  components: {
    Result,
  }
};
</script>

<template>
  <div id="app" class="content">
    <Result/>
  </div>
</template>

