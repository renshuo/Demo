<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <br/>
  <a-row>
    <a-col span="16">
      <a-button type="primary">Primary1</a-button>
    </a-col>
    <a-col span="8">
      <a-input width="300" type="number"></a-input>
    </a-col>
  </a-row>
  <a-button type="primary">Primary2</a-button>
  <a-button type="primary">Primary3</a-button>
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
