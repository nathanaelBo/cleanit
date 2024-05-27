<template>
  <v-app>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script lang="ts" setup>
import {useDataStore} from "./stores/app";

const dataStore = useDataStore();

dataStore.fetchData();
</script>
