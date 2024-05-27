<template>
  <v-app-bar color="primary">
    <v-app-bar-nav-icon @click.stop="toggleDrawer()"></v-app-bar-nav-icon>
    <v-toolbar-title>Clean It!</v-toolbar-title>
    <v-spacer></v-spacer>
  </v-app-bar>

  <v-navigation-drawer v-model="drawer" temporary>
    <v-list>
      <v-list-item v-for="item in items" :active="isActiveRoute(item.route)"
                   :key="item.route" @click="$router.push(item.route)">
        <v-list-item-title>{{ item.title }}</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {ref, Ref} from "vue";

const route = useRoute();

const drawer: Ref<boolean> = ref(false);
const items = [
  {
    title: 'Receive Textile',
    route: '/',
  },
  {
    title: 'Order Overview',
    route: '/overview',
  }
];

function toggleDrawer() {
  drawer.value = !drawer.value;
}

function isActiveRoute(itemRoute: string) {
  return route.path === itemRoute;
}
</script>

<style scoped lang="sass">

</style>
