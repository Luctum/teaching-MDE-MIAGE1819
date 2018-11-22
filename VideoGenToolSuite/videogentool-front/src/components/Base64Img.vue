<script>
export default {
  name: "Base64Img",
  props: {
    image: String
  },
  computed: {
    convertedImg() {
        return "data:image/png;base64," + this.image;
    }
  },
};
</script>


<template>
  <figure>
    <img :src="convertedImg" />
  </figure>
</template>
