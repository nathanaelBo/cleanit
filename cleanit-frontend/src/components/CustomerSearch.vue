<template>
  <v-autocomplete
    :items="customers"
    item-value="id"
    :item-title="item => `${item.firstName} ${item.lastName}`"
    label="Select Customer"
    :rules="validationRules"
    @update:search="searchCustomers"
    auto-select-first>
  </v-autocomplete>
</template>

<script setup lang="ts">
import axios from "axios";
import {Customer} from "../types/customer";
import debounce from 'lodash.debounce';
import {ref, Ref} from "vue";

const customers: Ref<Customer[]> = ref([]);

function resetCustomers() {
  customers.value = [];
}

const validationRules = [
  v => (v !== null && v !== '') || 'Field is required',
  v => !isNaN(parseFloat(v)) || 'Must be a number',
  v => parseFloat(v) > 0 || 'Must be greater than zero'
];

function searchCustomers(value: string) {
  debouncedGetCustomersByName(value);
  // await getCustomersByName(value); // not debounced
}

const debouncedGetCustomersByName = debounce(getCustomersByName, 250);

function getCustomersByName(name: string) {
  axios.get(import.meta.env.VITE_API_BASE_URL + '/customers?name=' + name)
    .then((res) => {
      customers.value = res.data as Customer[];
    })
    .catch((err) => {
      console.log(err)
    });
}

defineExpose({
  resetCustomers
});

</script>

<style scoped lang="sass">

</style>
