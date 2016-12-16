package rent.application;

import rent.application.singletons.Context;
import rent.model.entities.*;
import rent.model.services.impl.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Class for filling database with basic data.
 *
 * @author zagart
 */
public class DatabaseLoader {
    private static final String AVERAGE = "Обычный";
    private static final int BILLS_THRESHOLD = 10;
    private static final int BILL_BOUND = 10000;
    private static final String CHEAP = "Дешевый";
    private static final int CUSTOMERS_THRESHOLD = 10;
    private static final int EXPENSES_THRESHOLD = 10;
    private static final int EXPENSE_BOUND = 1000;
    private static final String EXPENSIVE = "Дорогой";
    private static final int HOUSE_INDEX_BOUND = 50;
    private static final int PASSPORTS_THRESHOLD = 10;
    private static final int PAYMENTS_THRESHOLD = 30;
    private static final int TIME_BOUND = Integer.MAX_VALUE;
    private static final String[] cities = new String[]{
            "Гродно",
            "Витебск",
            "Минск",
            "Гомель",
            "Могилев",
            "Брест"
    };
    private static final String[] firstNames = new String[]{
            "Иван",
            "Петр",
            "Александр",
            "Дмитрий",
            "Евгений",
            "Владислав"
    };
    private static final String[] lastNames = new String[]{
            "Иванов",
            "Петров",
            "Александров",
            "Дмитриев",
            "Евгеньев",
            "Владиславов"
    };
    private static final String[] patronymics = new String[]{
            "Иванович",
            "Петрович",
            "Александрович",
            "Дмитриевич",
            "Евгеньевич",
            "Владиславович"
    };
    private static final String[] regions = new String[]{
            "Ленинский РОВД",
            "Октябрьская РОВД",
            "Дзержинская РОВД"
    };
    private static final String[] streets = new String[]{
            "Горького",
            "Победы",
            "Ожешко",
            "Мира",
            "Дзержинского",
            "Льва Толстого"
    };

    public static void activate() {
        SingletonHolder.CONTEXT.loadDatabase();
    }

    private void associateBillsAndTariffs(final List<Bill> pBills, final List<Tariff> pTariffs) {
        final Random random = new Random();
        for (Bill pBill : pBills) {
            pTariffs.get(random.nextInt(pTariffs.size())).addBill(pBill);
        }
    }

    private void associateCustomerKeys(final List<Customer> pCustomers,
                                       final List<Payment> pPayments,
                                       final List<Expense> pExpenses,
                                       final List<Bill> pBills,
                                       final List<Passport> pPassports) {
        for (int i = 0; i < pCustomers.size(); i++) {
            final Customer customer = pCustomers.get(i);
            customer.addBill(pBills.get(i));
            customer.addPayment(pPayments.get(i));
            customer.addExpense(pExpenses.get(i));
            customer.addPassport(pPassports.get(i));
        }
    }

    private void associateKeys(List<Tariff> pTariffs,
                               List<Payment> pPayments,
                               List<Passport> pPassports,
                               List<Expense> pExpenses,
                               List<Bill> pBills,
                               List<Customer> pCustomers) {
        associateBillsAndTariffs(pBills, pTariffs);
        associateCustomerKeys(pCustomers, pPayments, pExpenses, pBills, pPassports);
    }

    private List<Bill> createBills() {
        final Random random = new Random();
        return new ArrayList<Bill>() {
            {
                for (int i = 0; i < BILLS_THRESHOLD; i++) {
                    add(new Bill()
                            .setBillDate(new Date(System.currentTimeMillis() - random.nextInt(TIME_BOUND)))
                            .setColdWaterBill((long) random.nextInt(BILL_BOUND))
                            .setGazBill((long) random.nextInt(BILL_BOUND))
                            .setHotWaterBill((long) random.nextInt(BILL_BOUND))
                            .setLightBill((long) random.nextInt(BILL_BOUND))
                    );
                }
            }
        };
    }

    private List<Customer> createCustomers() {
        final String ADDRESS_TEMPLATE = "г.%s, ул.%s, д.%d";
        final String PHONE_TEMPLATE = "%d-%d-%d";
        final Random random = new Random();
        return new ArrayList<Customer>() {
            {
                for (int i = 0; i < CUSTOMERS_THRESHOLD; i++) {
                    add(new Customer()
                            .setAddress(String.format(
                                    ADDRESS_TEMPLATE,
                                    cities[random.nextInt(cities.length)],
                                    streets[random.nextInt(streets.length)],
                                    random.nextInt(1 + HOUSE_INDEX_BOUND)))
                            .setFirstName(firstNames[random.nextInt(firstNames.length)])
                            .setLastName(lastNames[random.nextInt(lastNames.length)])
                            .setPatronymic(patronymics[random.nextInt(patronymics.length)])
                            .setPhoneNumber(String.format(
                                    PHONE_TEMPLATE,
                                    10 + random.nextInt(90),
                                    10 + random.nextInt(90),
                                    10 + random.nextInt(90)))
                    );
                }
            }
        };
    }

    private List<Expense> createExpenses() {
        final Random random = new Random();
        return new ArrayList<Expense>() {
            {
                for (int i = 0; i < EXPENSES_THRESHOLD; i++) {
                    add(new Expense()
                            .setColdWaterExpense((long) random.nextInt(EXPENSE_BOUND))
                            .setExpenseDate(new Date(System.currentTimeMillis() - random.nextInt(TIME_BOUND)))
                            .setGazExpense((long) random.nextInt(EXPENSE_BOUND))
                            .setHotWaterExpense((long) random.nextInt(EXPENSE_BOUND))
                            .setLightExpense((long) random.nextInt(EXPENSE_BOUND))
                    );
                }
            }
        };
    }

    private List<Passport> createPassports() {
        final Random random = new Random();
        return new ArrayList<Passport>() {
            {
                for (int i = 0; i < PASSPORTS_THRESHOLD; i++) {
                    add(new Passport()
                            .setId((long) (1000000 + random.nextInt(9000000)))
                            .setDateOfIssue(new Date(System.currentTimeMillis() - random.nextInt(TIME_BOUND)))
                            .setExpirationDate(new Date(System.currentTimeMillis() + random.nextInt(TIME_BOUND)))
                            .setIssuedBy(regions[random.nextInt(regions.length)])
                            .setPersonalNumber(String.valueOf(random.nextInt(Integer.MAX_VALUE)))
                    );
                }
            }
        };
    }

    private List<Payment> createPayments() {
        final Random random = new Random();
        return new ArrayList<Payment>() {
            {
                for (int i = 0; i < PAYMENTS_THRESHOLD; i++) {
                    add(new Payment()
                            .setAmount((long) random.nextInt(1000))
                            .setBacklog((long) random.nextInt(100))
                            .setTimestamp(new Date(System.currentTimeMillis() - random.nextInt(Integer.MAX_VALUE)))
                    );
                }
            }
        };
    }

    private List<Tariff> createTariffs() {
        return new ArrayList<Tariff>() {
            {
                add(new Tariff().setAmount(100L).setDate(new Date()).setName(CHEAP));
                add(new Tariff().setAmount(200L).setDate(new Date()).setName(AVERAGE));
                add(new Tariff().setAmount(300L).setDate(new Date()).setName(EXPENSIVE));
            }
        };
    }

    private void loadDatabase() {
        final List<Tariff> tariffs = createTariffs();
        final List<Payment> payments = createPayments();
        final List<Passport> passports = createPassports();
        final List<Expense> expenses = createExpenses();
        final List<Bill> bills = createBills();
        final List<Customer> customers = createCustomers();
        associateKeys(tariffs, payments, passports, expenses, bills, customers);
        Context.<CustomerService>getService(Customer.class).batchSave(customers);
        Context.<TariffService>getService(Tariff.class).batchSave(tariffs);
        Context.<ExpenseService>getService(Expense.class).batchSave(expenses);
        Context.<BillService>getService(Bill.class).batchSave(bills);
        Context.<PaymentService>getService(Payment.class).batchSave(payments);
        Context.<PassportService>getService(Passport.class).batchSave(passports);
    }

    private static class SingletonHolder {
        private static final DatabaseLoader CONTEXT = new DatabaseLoader();
    }
}
