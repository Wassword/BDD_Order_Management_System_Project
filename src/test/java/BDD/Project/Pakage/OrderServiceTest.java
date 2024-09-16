package BDD.Project.Pakage;

import BDD.Project.Pakage.repo.OrderRepository;
import BDD.Project.Pakage.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import BDD.Project.Pakage.model.Order;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;  // Correct static imports for JUnit
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Order order = new Order();
        order.setCustomerName("John Doe");

        when(orderRepository.save(order)).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);
        assertNotNull(createdOrder);
        assertEquals("John Doe", createdOrder.getCustomerName());
        verify(orderRepository, times(1)).save(order);  // Correct usage
    }

    @Test
    void testGetAllOrders() {
        Order order1 = new Order();
        order1.setCustomerName("John Doe");
        Order order2 = new Order();
        order2.setCustomerName("Jane Doe");

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();
        assertEquals(2, orders.size());
        verify(orderRepository, times(1)).findAll();  // Correct usage
    }

    @Test
    void testGetOrderById() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerName("John Doe");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1L);
        assertTrue(foundOrder.isPresent());
        assertEquals("John Doe", foundOrder.get().getCustomerName());
        verify(orderRepository, times(1)).findById(1L);  // Correct usage
    }

    @Test
    void testUpdateOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerName("John Doe");

        when(orderRepository.save(order)).thenReturn(order);

        Order updatedOrder = orderService.updateOrder(order);
        assertNotNull(updatedOrder);
        assertEquals("John Doe", updatedOrder.getCustomerName());
        verify(orderRepository, times(1)).save(order);  // Correct usage
    }

    @Test
    void testDeleteOrder() {
        Long orderId = 1L;

        doNothing().when(orderRepository).deleteById(orderId);
        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);  // Correct usage
    }

}
