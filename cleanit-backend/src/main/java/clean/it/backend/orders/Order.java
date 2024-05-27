package clean.it.backend.orders;

import clean.it.backend.cleaning.CleaningType;
import clean.it.backend.customers.Customer;
import clean.it.backend.textiles.TextileType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 64)
    private String notes;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "textile_type_id")
    private TextileType textileType;

    @ManyToOne
    @JoinColumn(name = "cleaning_type_id")
    private CleaningType cleaningType;
}
