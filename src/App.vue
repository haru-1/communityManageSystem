<template>
  <div id="app">
    <!-- <div id="nav"> -->
      <!-- <router-link to="/">Home</router-link> | -->
      <!-- <router-link to="/about">About</router-link> -->
    <!-- </div> -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="scss">
#app, body, html {
    height: 100%;
    padding: 0;
    margin: auto;
    background-color: #e9eef3;
    }
</style>
