<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <header>
    <div class="flex flex-wrap py-1 ">
      <nav class="text-lg flex justify-between gap-2">
        <RouterLink class="dark:text-cyan-300 text-cyan-700 hover:bg-cyan-100 hover:dark:bg-cyan-800"  to="/">Home</RouterLink>
        <RouterLink class="dark:text-cyan-300 text-cyan-700 hover:bg-cyan-100 hover:dark:bg-cyan-800" to="/about">About</RouterLink>
        <RouterLink class="dark:text-cyan-300 text-cyan-700 hover:bg-cyan-100 hover:dark:bg-cyan-800"  to="/devdocs">Dev Docs</RouterLink>
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
