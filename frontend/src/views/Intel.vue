<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'
const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';

type Intel = {
  id: number
  title: string
  description?: string
  content?: string
}
const intelList = ref([] as Intel[])

onMounted(async () => {
  const res = await fetch(`${API_URL}/intel/all`)
  intelList.value = await res.json() as Intel[]
})
</script>

<template>
  <div>
    <h1>Intel</h1>
    <RouterLink class="nav-link" to="/intel/new">
      Capture new Intel
    </RouterLink>
    <section class="flex flex-col gap-2">


      <RouterLink v-for="intel in intelList" :key="intel.id" :to="{ path: `/intel/view/${intel.id}` }" class="nav-link"
        title="Click to view this Intel">
        {{ intel.title }}
      </RouterLink>


    </section>
  </div>
</template>
