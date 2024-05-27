package clean.it.backend.orders;

import clean.it.backend.customers.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private long id;
    private String notes;
    private OrderState orderState;
    private Customer customer;
    private String textileTypeName;
    private String cleaningTypeName;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.notes = order.getNotes();
        this.orderState = order.getOrderState();
        this.customer = order.getCustomer();
        this.textileTypeName = order.getTextileType().getName();
        this.cleaningTypeName = order.getCleaningType().getName();
    }
}
