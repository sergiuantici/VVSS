package pizzashop;

import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;
import static org.junit.jupiter.api.Assertions.*;
import pizzashop.model.Payment;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

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

    @AfterEach
    public void afterEach() throws IOException {
              BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/payments.txt"));
              bufferedWriter.close();
    }

    @Test
    @Order(1)
    @Timeout(5)
    @Tag("disabled")
    @DisplayName("disabledTest")
    @Disabled
    public void addPayment_disabled() {
                fail();
    }

    @Order(1)
    @Timeout(5)
    @Tag("ECP")
    @DisplayName("ECP_TC1")
    public void addPayment_ECP_TC1() throws IOException {
        int table = 1;
        PaymentType type = PaymentType.Card;
        double amount = 250;
        runWithoutException(table, type, amount);
    }

    @Test
    @Order(2)
    @Timeout(5)
    @Tag("ECP")
    @DisplayName("ECP_TC2")
    public void addPayment_ECP_TC2() {
        int table = 10;
        PaymentType type = PaymentType.Card;
        double amount = 160;
        runWithException(table, type, amount);
    }

    @Test
    @Order(3)
    @Timeout(5)
    @Tag("ECP")
    @DisplayName("ECP_TC3")
    public void addPayment_ECP_TC3() {
        int table = 7;
        PaymentType type = null;
        double amount = 120;
        runWithException(table, type, amount);
    }

    @Test
    @Order(4)
    @Timeout(5)
    @Tag("ECP")
    @DisplayName("ECP_TC4")
    public void addPayment_TC4_ECP() {
        int table = 3;
        PaymentType type = PaymentType.Cash;
        double amount = -150;
        runWithException(table, type, amount);
    }

    private void runWithException(int table, PaymentType type, double amount) {
        assertThrows(Exception.class, () -> pizzaService.addPayment(table, type, amount));
    }

    private void runWithoutException(int table, PaymentType type, double amount) throws IOException {
        pizzaService.addPayment(table, type, amount);
        List<Payment> payments = readFromFile();
        assertEquals(1, payments.size());
        Payment payment = payments.get(0);
        assertEquals(table, payment.getTableNumber());
        assertEquals(type, payment.getType());
        assertEquals(amount, payment.getAmount());
    }

    private List<Payment> readFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("data/payments.txt"));
        String line;
        List<Payment> payments = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                        payments.add(new Payment(Integer.parseInt(split[0]),
                                        split[1].equals("Cash") ? PaymentType.Cash : PaymentType.Card,
                                        Double.parseDouble(split[2]))
                        );
            }
        bufferedReader.close();
        return payments;
    }



//    @Test
//    @Order(1)
//    @Timeout(2)
//    @DisplayName("addPaymentSuccessful test for addPayment() method from PizzaService that pass (1)")
//    public void addPaymentSuccessful() {
//        int numberOfEntities = paymentRepository.getAll().size();
//        int table = 1;
//        PaymentType paymentType = PaymentType.Card;
//        double amount = 500;
//        pizzaService.addPayment(table, paymentType, amount);
//        assertEquals(paymentRepository.getAll().size(), numberOfEntities + 1);
//    }
//
//    @Test
//    @Order(2)
//    @Timeout(2)
//    @DisplayName("addPaymentSuccessful test for addPayment() method from PizzaService that fails (1)")
//    public void addPaymentUnsuccessful() {
//        int numberOfEntities = paymentRepository.getAll().size();
//        int table = 0;
//        PaymentType paymentType = PaymentType.Card;
//        double amount = 500;
//        try {
//            pizzaService.addPayment(table, paymentType, amount);
//        } catch (RuntimeException exception) {
//            assertEquals(exception.getMessage(), "Error - invalid table!");
//        }
//        assertEquals(paymentRepository.getAll().size(), numberOfEntities);
//    }
//
//    @Test
//    @Order(3)
//    @Timeout(2)
//    @DisplayName("addPaymentSuccessful test for addPayment() method from PizzaService that pass (2)")
//    public void addPaymentSuccessfulSecond() {
//        int numberOfEntities = paymentRepository.getAll().size();
//        int table = 7;
//        PaymentType paymentType = PaymentType.Cash;
//        double amount = 20;
//        pizzaService.addPayment(table, paymentType, amount);
//        assertEquals(paymentRepository.getAll().size(), numberOfEntities + 1);
//    }
//
//    @Test
//    @Order(4)
//    @Timeout(2)
//    @DisplayName("addPaymentSuccessful test for addPayment() method from PizzaService that fails (2)")
//    public void addPaymentUnsuccessfulSecond() {
//        int numberOfEntities = paymentRepository.getAll().size();
//        int table = 2;
//        PaymentType paymentType = PaymentType.Card;
//        double amount = -20;
//        try {
//            pizzaService.addPayment(table, paymentType, amount);
//        } catch (RuntimeException exception) {
//            assertEquals(exception.getMessage(), "Error - invalid amount!");
//        }
//        assertEquals(paymentRepository.getAll().size(), numberOfEntities);
//    }
//
//    @Test
//    @Order(5)
//    @Timeout(2)
//    @Disabled
//    public void addPaymentSuccessfulThird() {
//    }
}
