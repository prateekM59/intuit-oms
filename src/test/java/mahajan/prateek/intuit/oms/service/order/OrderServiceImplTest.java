package mahajan.prateek.intuit.oms.service.order;

import mahajan.prateek.intuit.oms.api.model.order.CreateOrderRequest;
import mahajan.prateek.intuit.oms.exception.OMSBusinessException;
import mahajan.prateek.intuit.oms.repository.model.Order;
import mahajan.prateek.intuit.oms.repository.model.Product;
import mahajan.prateek.intuit.oms.repository.model.User;
import mahajan.prateek.intuit.oms.repository.model.UsersType;
import mahajan.prateek.intuit.oms.repository.order.OrderRepository;
import mahajan.prateek.intuit.oms.service.user.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by: pramahajan on 11/17/20 5:21 AM GMT+05:30
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private  UserService userService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void createOrder_withValidCreateOrderRequest_shouldCallOrderRepositoryOnce() throws OMSBusinessException {
        OrderService orderService = new OrderServiceImpl(orderRepository, userService);
        Integer userId = 616162, quantity = 5, orderId = 987651;
        String name = "name", address = "address", email = "email", phone = "9876544";

        User mockedUser = new User(name, email, address, phone, UsersType.NORMAL);
        mockedUser.setId(userId);

        Order mockedOrder = new Order();
        mockedOrder.setId(orderId);

        Mockito.when(userService.getUser(userId)).thenReturn(mockedUser);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(mockedOrder);

        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .withAddress(address)
                .withEmail(email)
                .withPhone(phone)
                .withUserId(userId)
                .withQuantity(quantity)
                .withProduct(Product.CHAIR.name())
                .build();

        Assert.assertEquals(orderId, orderService.createOrder(createOrderRequest));
        Mockito.verify(userService).getUser(userId);

        Mockito.verify(orderRepository).save(Mockito.any(Order.class));
    }

    @Test(expected=OMSBusinessException.class)
    public void createOrder_withInvalidProduct_shouldThrowException() throws OMSBusinessException {
        OrderService orderService = new OrderServiceImpl(orderRepository, userService);
        Integer userId = 616162, quantity = 5, orderId = 987651;
        String name = "name", address = "address", email = "email", phone = "9876544";

        User mockedUser = new User(name, email, address, phone, UsersType.NORMAL);
        mockedUser.setId(userId);

        Order mockedOrder = new Order();
        mockedOrder.setId(orderId);

        Mockito.when(userService.getUser(userId)).thenReturn(mockedUser);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(mockedOrder);

        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .withAddress(address)
                .withEmail(email)
                .withPhone(phone)
                .withUserId(userId)
                .withQuantity(quantity)
                .withProduct("Apple")
                .build();

        try {
            orderService.createOrder(createOrderRequest);
        } catch (Throwable e) {
            throw e;
        } finally {
            Mockito.verify(userService, Mockito.times(0)).getUser(Mockito.anyInt());
            Mockito.verify(orderRepository, Mockito.times(0)).save(Mockito.any(Order.class));
        }
    }

}
