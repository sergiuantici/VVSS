package pizzashop;

import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private MenuRepository menuRepository;
    private PizzaService pizzaService;

    @BeforeEach
    public void setup() {
        paymentRepository = new PaymentRepository();
        menuRepository = new MenuRepository();
        pizzaService = new PizzaService(menuRepository, paymentRepository);
    }

    @Test
    @Order(1)
    @Timeout(2)
    @DisplayName("addPaymentSuccessful test for addPayment() method from PizzaService that pass (1)")
    public void addPaymentSuccessful() {
        int numberOfEntities = paymentRepository.getAll().size();
        int table = 1;
        PaymentType paymentType = PaymentType.Card;
        double amount = 500;
        pizzaService.addPayment(table, paymentType, amount);
        assertEquals(paymentRepository.getAll().size(), numberOfEntities + 1);
    }

    @Test
    @Order(2)
    @Timeout(2)
    @DisplayName("addPaymentSuccessful test for addPayment() method from PizzaService that fails (1)")
    public void addPaymentUnsuccessful() {
        int numberOfEntities = paymentRepository.getAll().size();
        int table = 0;
        PaymentType paymentType = PaymentType.Card;
        double amount = 500;
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (RuntimeException exception) {
            assertEquals(exception.getMessage(), "Error - invalid table!");
        }
        assertEquals(paymentRepository.getAll().size(), numberOfEntities);
    }

    @Test
    @Order(3)
    @Timeout(2)
    @DisplayName("addPaymentSuccessful test for addPayment() method from PizzaService that pass (2)")
    public void addPaymentSuccessfulSecond() {
        int numberOfEntities = paymentRepository.getAll().size();
        int table = 7;
        PaymentType paymentType = PaymentType.Cash;
        double amount = 20;
        pizzaService.addPayment(table, paymentType, amount);
        assertEquals(paymentRepository.getAll().size(), numberOfEntities + 1);
    }

    @Test
    @Order(4)
    @Timeout(2)
    @DisplayName("addPaymentSuccessful test for addPayment() method from PizzaService that fails (2)")
    public void addPaymentUnsuccessfulSecond() {
        int numberOfEntities = paymentRepository.getAll().size();
        int table = 2;
        PaymentType paymentType = PaymentType.Card;
        double amount = -20;
        try {
            pizzaService.addPayment(table, paymentType, amount);
        } catch (RuntimeException exception) {
            assertEquals(exception.getMessage(), "Error - invalid amount!");
        }
        assertEquals(paymentRepository.getAll().size(), numberOfEntities);
    }

    @Test
    @Order(5)
    @Timeout(2)
    @Disabled
    public void addPaymentSuccessfulThird() {
    }
}
