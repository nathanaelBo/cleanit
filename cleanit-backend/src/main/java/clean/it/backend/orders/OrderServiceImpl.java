package clean.it.backend.orders;

import clean.it.backend.cleaning.CleaningType;
import clean.it.backend.customers.Customer;
import clean.it.backend.common.exceptions.ResourceNotFoundException;
import clean.it.backend.cleaning.CleaningTypeRepository;
import clean.it.backend.customers.CustomerRepository;
import clean.it.backend.textiles.TextileTypeRepository;
import clean.it.backend.textiles.TextileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CleaningTypeRepository cleaningTypeRepository;
    private final TextileTypeRepository textileTypeRepository;

    @Override
    public Optional<OrderDto> getOrderById(long id) {
        return orderRepository.findById(id)
                .map(OrderDto::new);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDto::new)
                .toList();
    }

    @Override
    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        // get relationships by id
        Customer customer = customerRepository.findById(createOrderDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid customer ID"));

        TextileType textileType = textileTypeRepository.findById(createOrderDto.getTextileTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid textile type ID"));

        CleaningType cleaningType = cleaningTypeRepository.findById(createOrderDto.getCleaningTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid cleaning type ID"));

        // create order
        Order order = new Order();
        order.setOrderState(OrderState.RECEIVED);
        order.setCustomer(customer);
        order.setTextileType(textileType);
        order.setCleaningType(cleaningType);
        order.setNotes(createOrderDto.getNotes());

        Order createdOrder = orderRepository.save(order);
        return new OrderDto(createdOrder);
    }
}
