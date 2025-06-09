<script setup lang="ts">

import { reactive, onMounted } from 'vue'

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

const intel = reactive({
  id: 0,
  title: '',
  description: '',
  content: ''
})

const annotation = reactive({
  start: 0,
  end: 0,
  text: '',
})

const annotations = reactive([] as typeof annotation[])

function clearAnnotations() {
  // Add your locking logic here
  console.log('Clearing Annotations:', intel)
  // if the status is locked we have to write a dialog to confirm the lock which also clears the intel from all previous annotations

  // Locking it will be a database operation. It is not of big consequence to lock but it is a big consequence to unlock.
  // So we will not allow unlocking for now.
  // We will also not allow editing after locking.
  // This is to prevent a complicated review process.

}

function submitAnnotations() {
  // Send the intel object as JSON to the backend
  fetch(`${API_URL}/intel/annotate`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(intel),
  })
    .then(response => response.text())
    .then(data => {
      console.log('Response from backend:', data)
      // Optionally, show a success message or redirect
    })
    .catch(error => {
      console.error('Error submitting intel:', error)
      // Optionally, show an error message
    })
}


function fetchIntel(id: string) {
  fetch(`${API_URL}/api/intel/${id}`)
    .then(response => response.json())
    .then(data => {
      intel.id = data.id
      intel.title = data.title
      intel.description = data.description
      intel.content = data.content
    })
    .catch(error => {
      console.error('Error fetching intel:', error)
    })
}

onMounted(() => {
  const urlParams = new URLSearchParams(window.location.search);
  // http://localhost:5173/intel/view/68455143b217844be5856ad1
  const intelId = window.location.pathname.split('/').pop();
  console.log('Intel ID from URL:', intelId);
  if (intelId) {
    fetchIntel(intelId);
  }
});

</script>
<template>
  <div>

    <h1>Annotate Intel</h1>
    <form @submit.prevent="submitAnnotations" class="flex flex-col gap-4">
      <h1>Title: {{ intel.title }}</h1>
      <input type="hidden" name="id" v-model="intel.id" />

      <textarea name="intel" id="intel" readonly v-model="intel.content"></textarea>
      <div class="flex gap-4">

        <button type="submit">Save Annotations</button>
        <button type="button" @click.prevent="clearAnnotations">Clear</button>

        <button type="button" popovertarget="help" popoveraction="toggle">help</button>
      </div>

    </form>


    <div popover id="help" class="big flex-col gap-4">

    </div>

  </div>
</template>
