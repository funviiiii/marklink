<template>
  <mavon-editor v-model="content"></mavon-editor>
</template>

<script setup>
import {mavonEditor} from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

import {ref} from "vue";

let content = ref("")
</script>

<style scoped>

</style>