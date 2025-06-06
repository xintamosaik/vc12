<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <header>
    <div class="flex flex-wrap py-1 ">
      <nav class="text-lg flex justify-between gap-2">
        <RouterLink class="nav-link" to="/">
          Home
        </RouterLink>
        <RouterLink class="nav-link" to="/drafts">
          Drafts
        </RouterLink>
        <RouterLink class="nav-link" to="/signals">
          Signals
        </RouterLink>
        <RouterLink class="nav-link" to="/intel">
          Intel
        </RouterLink>
 
      </nav>
    </div>
  </header>

  <RouterView />
</template>

<style scoped>
nav a.router-link-exact-active {
  color: unset;
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}
</style>
