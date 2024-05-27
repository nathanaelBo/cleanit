package clean.it.backend.orders.service;

import clean.it.backend.orders.CreateOrderDto;
import clean.it.backend.orders.OrderDto;
import clean.it.backend.cleaning.CleaningType;
import clean.it.backend.customers.Customer;
import clean.it.backend.orders.Order;
import clean.it.backend.textiles.TextileType;
import clean.it.backend.common.exceptions.ResourceNotFoundException;
import clean.it.backend.cleaning.CleaningTypeRepository;
import clean.it.backend.customers.CustomerRepository;
import clean.it.backend.orders.OrderRepository;
import clean.it.backend.textiles.TextileTypeRepository;
import clean.it.backend.orders.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Arrange, Act, Assert
// MethodName_StateUnderTest_ExpectedBehavior
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CleaningTypeRepository cleaningTypeRepository;
    @Mock
    private TextileTypeRepository textileTypeRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getOrderById_OrderExists_ReturnsOrderDto() {
        // Arrange
        Order order = setupOrder(1L, "Anzug", "Express");
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        Optional<OrderDto> result = orderService.getOrderById(1L);

        // Assert
        assertTrue(result.isPresent(), "Order should be found");
        assertEquals(1L, result.get().getId());
    }

    @Test
    void getOrderById_OrderDoesNotExist_ReturnsEmpty() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<OrderDto> result = orderService.getOrderById(1L);

        // Assert
        assertFalse(result.isPresent(), "Order should not be found");
    }

    @Test
    void getAllOrders_MultipleOrders_ReturnsListOfOrders() {
        // Arrange
        Order order1 = setupOrder(1L, "Hemd", "Express");
        Order order2 = setupOrder(2L, "Hose", "Standard");
        when(orderRepository.findAll()).thenReturn(List.of(order1, order2));

        // Act
        List<OrderDto> result = orderService.getAllOrders();

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Should return two orders");
    }

    @Test
    void createOrder_ValidData_ReturnsCreatedOrder() {
        // Arrange
        CreateOrderDto dto = new CreateOrderDto();
        dto.setCustomerId(1L);
        dto.setTextileTypeId(1L);
        dto.setCleaningTypeId(1L);
        dto.setNotes("Test notes");

        Customer customer = new Customer();
        customer.setId(1L);
        TextileType textileType = new TextileType();
        textileType.setId(1L);
        CleaningType cleaningType = new CleaningType();
        cleaningType.setId(1L);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(textileTypeRepository.findById(1L)).thenReturn(Optional.of(textileType));
        when(cleaningTypeRepository.findById(1L)).thenReturn(Optional.of(cleaningType));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        OrderDto createdOrder = orderService.createOrder(dto);

        // Assert
        verify(orderRepository, times(1)).save(any(Order.class));
        assertNotNull(createdOrder, "Created order should not be null");
        assertEquals("Test notes", createdOrder.getNotes());
    }

    @Test
    void createOrder_InvalidCustomerId_ThrowsException() {
        // Arrange
        CreateOrderDto dto = new CreateOrderDto();
        dto.setCustomerId(99L);  // Assuming 99L is an invalid ID
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> orderService.createOrder(dto),
                "Should throw an exception when customer ID is invalid");
    }

    private Order setupOrder(long id, String textileTypeName, String cleaningTypeName) {
        TextileType textileType = new TextileType();
        textileType.setName(textileTypeName);

        CleaningType cleaningType = new CleaningType();
        cleaningType.setName(cleaningTypeName);

        Order order = new Order();
        order.setId(id);
        order.setTextileType(textileType);
        order.setCleaningType(cleaningType);

        return order;
    }
}
