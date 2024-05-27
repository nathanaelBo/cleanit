<template>
  <v-container>
    <v-sheet class="mx-auto" width="600">
      <h1>Receive Textile</h1>
      <v-form @submit.prevent="createOrder">
        <CustomerSearch v-model="orderData.customerId"
                        ref="customerSearchRef"/>

        <v-select
          v-model="orderData.textileTypeId"
          :items="textileTypes"
          item-value="id"
          item-title="name"
          label="Select Textile Type"
          :rules="idRules"
        ></v-select>

        <v-select
          v-model="orderData.cleaningTypeId"
          :items="cleaningTypes"
          item-value="id"
          item-title="name"
          label="Select Cleaning Type"
          :rules="idRules"
        ></v-select>

        <v-text-field
          v-model="orderData.notes"
          label="Notes"
          :counter="64"
          :rules="notesRules"
        ></v-text-field>

        <v-btn class="mt-2" type="submit" block>Submit</v-btn>
      </v-form>

      <v-snackbar :color="snackbar.color" v-model="snackbar.show">
        {{ snackbar.message }}
        <template v-slot:actions>
          <v-btn
            color="white"
            variant="text"
            @click="snackbar.show = false">
            Close
          </v-btn>
        </template>
      </v-snackbar>

    </v-sheet>
  </v-container>
</template>

<script setup lang="ts">
import {computed, Ref, ref} from 'vue';
import {useDataStore} from "../stores/app";
import axios from "axios";
import {Order} from "../types/order";
import {CreateOrderDto} from "../dtos/create-order-dto";
import {SnackbarConfig} from "../types/snackbar-config";
import CustomerSearch from "./CustomerSearch.vue";

const store = useDataStore();

const textileTypes = computed(() => store.textileTypes);
const cleaningTypes = computed(() => store.cleaningTypes);

const orderData: Ref<CreateOrderDto> = ref(new CreateOrderDto());

const customerSearchRef = ref(null);

const snackbar: Ref<SnackbarConfig> = ref(
  new SnackbarConfig(false, null, null)
);

const notesRules = [
  v => (v || '').length <= 64 || 'Max 64 characters'
];

const idRules = [
  v => (v !== null && v !== '') || 'Field is required',
  v => !isNaN(parseFloat(v)) || 'Must be a number',
  v => parseFloat(v) > 0 || 'Must be greater than zero'
];

async function createOrder() {
  await axios.post(import.meta.env.VITE_API_BASE_URL + '/orders', orderData.value)
    .then((res) => {
      const order: Order = res.data;
      store.addOrder(order);
      orderData.value = new CreateOrderDto();
      customerSearchRef.value.resetCustomers();
      snackbar.value = new SnackbarConfig(true, 'Order created', 'success');
    })
    .catch((err) => {
      const errorMessage = err.response?.data || 'Unexpected error occurred';
      snackbar.value = new SnackbarConfig(true, errorMessage, 'error');
    });
}

</script>

<style scoped lang="sass">

</style>
