<script setup lang="ts">
import { reactive } from 'vue'

const intel = reactive({
  title: '',
  description: ''
})

function submitIntel() {
  // Add your submission logic here
  console.log('Submitting Intel:', intel)
}
</script>
<template>
  <div>
    <h1>Capture New Intel</h1>
    <form @submit.prevent="submitIntel">
      <div>
        <label for="title">Title:</label>
        <input type="text" id="title" v-model="intel.title" required />
      </div>
      <div>
        <label for="description">Description:</label>
        <textarea id="description" v-model="intel.description" required></textarea>
      </div>
      <button type="submit">Submit Intel</button>
    </form>
    
    
  </div>
</template>



