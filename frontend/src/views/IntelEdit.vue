<script setup lang="ts">

import { reactive, onMounted, ref } from 'vue'

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';
const annotationDialog = ref<HTMLDialogElement | null>(null)

const intel = reactive({
  id: 0,
  title: '',
  description: '',
  content: ''
})

type Annotation = {
  start: number
  end: number
  marked: string
  keywords: string[]
}

const annotation = reactive({
  start: 0,
  end: 0,
  marked: '',
  keywords: [] as string[],
}) as Annotation

const annotations = reactive([] as Annotation[])

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

function askForKeyword(event: Event) {
  const selection = window.getSelection();
  console.log('Selection:', selection);
  if (selection && selection.rangeCount > 0) {
    const range = selection.getRangeAt(0);
    annotation.start = range.startOffset;
    annotation.end = range.endOffset;
    annotation.marked = selection.toString();
    console.log('Selected text:', annotation.marked);
    // Here you can open a dialog or a prompt to ask for the keyword
    
    const dialog = document.getElementById('annotation') as HTMLDialogElement;
    if (dialog) {
      dialog.showModal();
    } 


  }
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
function addKeywordOnEnter(event: KeyboardEvent) {
  if (event.key === 'Enter') {
    event.preventDefault(); // Prevent form submission
    addKeyword(event);
  }
}
function addKeyword(event: Event) {
  const currentAnnotation = annotation as Annotation;
  console.log('Adding keyword to annotation:', currentAnnotation);
  const input = document.getElementById('annotation-text') as HTMLInputElement;
  if (input && input.value) {
    const keywords = input.value.split(',').map(keyword => keyword.trim());
    currentAnnotation.keywords.push(...keywords);
    console.log('Updated keywords:', currentAnnotation.keywords);
    input.value = ''; // Clear the input field after adding keywords
  }

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

    <h1>Annotate Intel "{{ intel.title }}"</h1>
    <form @submit.prevent="submitAnnotations" class="flex flex-col gap-4">
      
      <input type="hidden" name="id" v-model="intel.id" />

      <p id="intel" 
        @mouseup="askForKeyword">
        {{ intel.content }}
    </p>
      <div class="flex gap-4">

        <button type="submit">Save Annotations</button>
        <button type="button" @click.prevent="clearAnnotations">Clear</button>

        <button type="button" popovertarget="help" popoveraction="toggle">help</button>
      </div>
      <dialog id="annotation" ref="annotationDialog" class="dark:bg-black dark:text-white bg-white text-black flex-col p-4 gap-4" @submit.prevent="addKeyword">
        <h2>Annotation</h2>
        <p>Selected text: <strong>{{ annotation.marked }}</strong></p>
        <p>Start: {{ annotation.start }}, End: {{ annotation.end }}</p>
        <p>Keywords:</p>
        <ul>
          <li v-for="(keyword, index) in annotation.keywords" :key="index">{{ keyword }}</li>
        </ul>
        <p>Enter keywords for the selected text:</p>
        <p>Note: You can add multiple keywords separated by commas.</p>
        <p>Example: "keyword1, keyword2, keyword3"</p>
        <label for="annotation-text">Add Keywords</label>
        <input type="text" id="annotation-text" @keydown="addKeywordOnEnter" />
        <button type="button" @click="addKeyword">Add Annotation</button>
   
        <button type="button" @click="annotationDialog?.close()">Close</button>
     
      </dialog>
    </form>


    <div popover id="help" class="big flex-col gap-4">

    </div>

  </div>
</template>
<style scoped>
  dialog {
    width: 400px;
    max-width: 90vw;
  }
  dialog:backdrop {
    background-color: rgba(0, 0, 0, 0.5);
  }
  dialog[open] {
    display: flex;

  }
</style>
