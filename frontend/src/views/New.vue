<script setup lang="ts">
import { reactive } from 'vue'

const intel = reactive({
  title: '',
  description: '',
  content: ''
})


function lockIntel() {
  // Add your locking logic here
  console.log('Locking Intel:', intel)
  // if the status is locked we have to write a dialog to confirm the lock which also clears the intel from all previous annotations
  
  // Locking it will be a database operation. It is not of big consequence to lock but it is a big consequence to unlock.
  // So we will not allow unlocking for now.
  // We will also not allow editing after locking.
  // This is to prevent a complicated review process.
  
}
function submitIntel() {
  // Add your submission logic here
  console.log('Submitting Intel:', intel)
  // API endpoint will be something like /api/intel/new
  // Has to be a POST request with the intel object in the body
}


</script>
<template>
  <div>
   
    <h1>Capture New Intel</h1>
    <form @submit.prevent="submitIntel" class="flex flex-col gap-4">
      <div class="flex flex-col">
        <label title="This should be a brief summary of the content. It will show on tooltips and in the Intel list." for="title">Title*</label>
        <input type="text" id="title" v-model="intel.title" required />
      </div>

      <div class="flex flex-col">
        <label for="description" title="This is optional, but can help you to remmber where the intel came from.">Description</label>
        <textarea id="description" v-model="intel.description"></textarea>
      </div>
      <div class="flex flex-col">
        <label for="description" title="This is the main body of the Intel. It can be a long text, or a short summary. See help for more.">Content</label>
        <textarea id="description" v-model="intel.content" rows="20" required></textarea>
      </div>
      <div class="flex gap-4">
        <button type="submit">Submit Intel</button>
        <button type="button" @click.prevent="lockIntel">Lock</button>
        
      <button type="button" popovertarget="help" popoveraction="toggle">help</button>
      </div>

    </form>


    <div popover id="help" class="big">
      <h2>Title</h2>
      <p>
        The title of the Intel. This should be a brief summary of the content. It will show on tooltips and in the Intel
        list.
      </p>
      <h2>Description</h2>
      <p>
        The description of the Intel. This is optional, but can help you to remmber where the intel came from.
      </p>
      <h2>Content</h2>
      <p>
        The content of the Intel. This is the main body of the Intel. It can be a long text, or a short summary.
      </p>
      <p>
        The content should be in plain text or markdown. Markdown will just enhance the readability of the content, but
        is not required.
      </p>
      <h2>Lock</h2>
      <p>
        The lock button will lock the Intel, preventing further edits. This is necessary to start the review process. At
        this point we don't support edits after annotations, becaues it requires a compicated review process.
      </p>
    </div>

  </div>
</template>
