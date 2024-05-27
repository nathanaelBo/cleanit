<template>
  <v-container>
    <h1>Order Overview</h1>
    <v-text-field
      v-model="search"
      label="Search"
      prepend-inner-icon="mdi-magnify"
      variant="outlined"
      hide-details
      single-line class="my-2"
    ></v-text-field>
    <v-data-table
      :headers="headers"
      :items="orders"
      :loading="ordersLoading"
      :search="search"
      class="elevation-1">
      <template v-slot:item.orderState="{ item }">
        <v-chip
          :color="statusColor(item.orderState)">
          {{ item.orderState }}
        </v-chip>
      </template>

      <template v-slot:loading>
        <v-skeleton-loader type="table-row@10"></v-skeleton-loader>
      </template>
    </v-data-table>
  </v-container>
</template>

<script setup lang="ts">
import {useDataStore} from "../stores/app";
import {computed, Ref, ref} from "vue";
import {Order} from "../types/order";

const store = useDataStore();
store.fetchOrders();

const orders = computed<Order[]>(() => store.orders);
const ordersLoading = computed<boolean>(() => store.ordersLoading);
const search: Ref<string> = ref('');

const headers = [
  {title: 'ID', key: 'id'},
  {title: 'Customer', key: 'fullName',
    value: item => `${item.customer.firstName} ${item.customer.lastName}`},
  {title: 'Textile Type', key: 'textileTypeName'},
  {title: 'Cleaning Type', key: 'cleaningTypeName'},
  {title: 'Order State', key: 'orderState'},
  {title: 'Notes', key: 'notes'}
];

function statusColor(status: string) {
  switch (status) {
    case 'RECEIVED':
      return 'orange';
    case 'CLEANED':
      return 'blue';
    case 'RETURNED':
      return 'green';
    default:
      return 'grey';
  }
}

</script>

<style scoped lang="sass">

</style>
