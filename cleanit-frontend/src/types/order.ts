import {Customer} from "./customer";

export interface Order {
  id: number;
  notes: string;
  orderState: string;
  customer: Customer;
  textileTypeName: string;
  cleaningTypeName: string;
}
