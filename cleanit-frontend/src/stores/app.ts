// Utilities
import {defineStore} from 'pinia'
import {Order} from "../types/order";
import axios from "axios";

export const useDataStore = defineStore('data', {
  state: () => ({
    orders: [] as Order[],
    ordersLoading: false,
    customers: [],
    cleaningTypes: [],
    textileTypes: []
  }),
  actions: {
    addOrder(order: Order) {
      this.orders.push(order);
    },

    async fetchOrders() {
      this.ordersLoading = true;
      await axios.get(import.meta.env.VITE_API_BASE_URL + '/orders')
        .then((res) => {
          this.orders = res.data;
        }).catch((err) => {
          console.error('Error fetching types:', err);
        })
        .finally(() => {
          this.ordersLoading = false;
        });
    },

    async fetchCleaningTypes() {
      await axios.get(import.meta.env.VITE_API_BASE_URL + '/cleaning-types')
        .then((res) => {
          this.cleaningTypes = res.data;
        }).catch((err) => {
          console.error('Error fetching types:', err);
        });
    },

    async fetchTextileTypes() {
      await axios.get(import.meta.env.VITE_API_BASE_URL + '/textile-types')
        .then((res) => {
          this.textileTypes = res.data;
        }).catch((err) => {
          console.error('Error fetching types:', err);
        });
    },

    async fetchData() {
      await Promise.all([
        this.fetchOrders(),
        this.fetchCleaningTypes(),
        this.fetchTextileTypes()
      ]);
    }
  }
})
