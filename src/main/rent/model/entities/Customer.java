package rent.model.entities;

import javafx.scene.control.cell.PropertyValueFactory;
import rent.interfaces.IEntity;
import rent.ui.entities.UiCustomer;
import rent.application.managers.TableManager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for Customer model.
 *
 * @author zagart
 */
@Entity
@Table(name = "customer", catalog = "rent")
public class Customer implements IEntity<UiCustomer> {
    public static final String MENU_NAME = "Заказчики";
    private String mAddress;
    private List<Bill> mBillList = new ArrayList<>();
    private List<Expense> mExpenseList = new ArrayList<>();
    private String mFirstName;
    private Long mId;
    private String mLastName;
    private Passport mPassport;
    private String mPatronymic;
    private List<Payment> mPaymentList = new ArrayList<>();
    private String mPhoneNumber;

    public void addBill(final Bill pBill) {
        mBillList.add(pBill);
        pBill.setCustomer(this);
    }

    public void addExpense(final Expense pExpense) {
        mExpenseList.add(pExpense);
        pExpense.setCustomer(this);
    }

    public void addPassport(final Passport pPassport) {
        mPassport = pPassport;
        pPassport.setCustomer(this);
    }

    public void addPayment(final Payment pPayment) {
        mPaymentList.add(pPayment);
        pPayment.setCustomer(this);
    }

    @Override
    public TableManager<UiCustomer> createTableManager() {
        final ArrayList<PropertyValueFactory> factories = new ArrayList<PropertyValueFactory>() {
            {
                add(new PropertyValueFactory<UiCustomer, String>(UiCustomer.Fields.ID));
                add(new PropertyValueFactory<UiCustomer, String>(UiCustomer.Fields.FIRST_NAME));
                add(new PropertyValueFactory<UiCustomer, String>(UiCustomer.Fields.LAST_NAME));
                add(new PropertyValueFactory<UiCustomer, String>(UiCustomer.Fields.PATRONYMIC));
                add(new PropertyValueFactory<UiCustomer, String>(UiCustomer.Fields.ADDRESS));
                add(new PropertyValueFactory<UiCustomer, String>(UiCustomer.Fields.PHONE_NUMBER));
            }
        };
        return new TableManager<>(
                factories,
                Fields.ID,
                Fields.FIRST_NAME,
                Fields.LAST_NAME,
                Fields.PATRONYMIC,
                Fields.ADDRESS,
                Fields.PHONE_NUMBER);
    }

    @Override
    public UiCustomer createTableModel() {
        return new UiCustomer(this);
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    public Customer setId(Long pId) {
        mId = pId;
        return this;
    }

    @Column(name = "address")
    public String getAddress() {
        return mAddress;
    }

    public Customer setAddress(String pAddress) {
        mAddress = pAddress;
        return this;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    public List<Bill> getBillList() {
        return mBillList;
    }

    public Customer setBillList(List<Bill> pBillList) {
        mBillList = pBillList;
        return this;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Expense.class, mappedBy = "customer")
    public List<Expense> getExpenseList() {
        return mExpenseList;
    }

    public Customer setExpenseList(List<Expense> pExpenseList) {
        mExpenseList = pExpenseList;
        return this;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return mFirstName;
    }

    public Customer setFirstName(String pFirstName) {
        mFirstName = pFirstName;
        return this;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return mLastName;
    }

    public Customer setLastName(String pLastName) {
        mLastName = pLastName;
        return this;
    }

    @OneToOne(targetEntity = Passport.class, mappedBy = "customer")
    public Passport getPassport() {
        return mPassport;
    }

    public void setPassport(Passport pPassport) {
        mPassport = pPassport;
    }

    @Column(name = "patronymic")
    public String getPatronymic() {
        return mPatronymic;
    }

    public Customer setPatronymic(String pPatronymic) {
        mPatronymic = pPatronymic;
        return this;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Payment.class, mappedBy = "customer")
    public List<Payment> getPaymentList() {
        return mPaymentList;
    }

    public Customer setPaymentList(List<Payment> pPaymentList) {
        mPaymentList = pPaymentList;
        return this;
    }

    @Column(name = "phone")
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public Customer setPhoneNumber(String pPhoneNumber) {
        mPhoneNumber = pPhoneNumber;
        return this;
    }

    public interface Fields {
        String ADDRESS = "Адрес";
        String FIRST_NAME = "Имя";
        String ID = "ID";
        String LAST_NAME = "Фамилия";
        String PATRONYMIC = "Отчество";
        String PHONE_NUMBER = "Телефон";
    }
}
