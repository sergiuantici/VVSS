package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo) {
        this.menuRepo = menuRepo;
        this.payRepo = payRepo;
    }

    public List<MenuDataModel> getMenuData() {
        return menuRepo.getMenu();
    }

    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    public void addPayment(int table, PaymentType type, double amount) throws RuntimeException {
        if (table > 8 || table < 1) {
            throw new RuntimeException("Error - invalid table!");
        }

        if (type == null) {
            throw new RuntimeException("Error - invalid type!");
        }

        if (amount <= 0) {
            throw new RuntimeException("Error - invalid amount!");
        }

        Payment payment = new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        List<Payment> l = getPayments();
        if(l.size() == 0)
        {
            total = 0.0;
        }
        else if(l == null)
        {
            total = 0.0;
        }
        else {
            for (Payment p : l) {
                if (p.getType().equals(type))
                    total += p.getAmount();
            }
        }
        return total;
    }

}
