package pizzashop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setup() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    @DisplayName("addPaymentSuccessful test")
    public void addPaymentSuccessful() {
        int numberOfEntities = paymentRepository.getAll().size();
        int table = 1;
        PaymentType paymentType = PaymentType.Card;
        double amount = 500;
        Payment payment = new Payment(table, paymentType, amount);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().size(), numberOfEntities + 1);
    }

    @Test
    public void addPaymentUnsuccessful() {
        int numberOfEntities = paymentRepository.getAll().size();
        int table = 1;
        PaymentType paymentType = PaymentType.Card;
        double amount = 500;
        Payment payment = new Payment(table, paymentType, amount);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().size(), numberOfEntities + 1);
    }

    @Test
    public void addPaymentSuccessfulSecond() {
        int numberOfEntities = paymentRepository.getAll().size();
        int table = 1;
        PaymentType paymentType = PaymentType.Card;
        double amount = 500;
        Payment payment = new Payment(table, paymentType, amount);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().size(), numberOfEntities + 1);
    }

    @Test
    public void addPaymentUnsuccessfulSecond() {
        int numberOfEntities = paymentRepository.getAll().size();
        int table = 1;
        PaymentType paymentType = PaymentType.Card;
        double amount = 500;
        Payment payment = new Payment(table, paymentType, amount);
        paymentRepository.add(payment);
        assertEquals(paymentRepository.getAll().size(), numberOfEntities + 1);
    }

    @Test
    @Disabled
    public void addPaymentSuccessfulThird() {
    }
}
