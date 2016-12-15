package rent.model.entities;

import rent.interfaces.IEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for Customer model.
 *
 * @author zagart
 */
@Entity
@Table(name = "customer", catalog = "rent")
public class Customer implements IEntity {
    private String mAddress;
    private List<Bill> mBillList;
    private List<Expense> mExpenseList;
    private String mFirstName;
    private Long mId;
    private String mLastName;
    private String mPassportId;
    private String mPatronymic;
    private List<Payment> mPaymentList;
    private String mPhoneNumber;

    @Column(name = "address")
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String pAddress) {
        mAddress = pAddress;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Bill.class, mappedBy = "customerId")
    public List<Bill> getBillList() {
        return mBillList;
    }

    public void setBillList(List<Bill> pBillList) {
        mBillList = pBillList;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Expense.class, mappedBy = "customerId")
    public List<Expense> getExpenseList() {
        return mExpenseList;
    }

    public void setExpenseList(List<Expense> pExpenseList) {
        mExpenseList = pExpenseList;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String pFirstName) {
        mFirstName = pFirstName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    public void setId(Long pId) {
        mId = pId;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String pLastName) {
        mLastName = pLastName;
    }

    @OneToOne(targetEntity = Passport.class, mappedBy = "id")
    public String getPassportId() {
        return mPassportId;
    }

    public void setPassportId(String pPassportId) {
        mPassportId = pPassportId;
    }

    @Column(name = "patronymic")
    public String getPatronymic() {
        return mPatronymic;
    }

    public void setPatronymic(String pPatronymic) {
        mPatronymic = pPatronymic;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Payment.class, mappedBy = "customerId")
    public List<Payment> getPaymentList() {
        return mPaymentList;
    }

    public void setPaymentList(List<Payment> pPaymentList) {
        mPaymentList = pPaymentList;
    }

    @Column(name = "phone")
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String pPhoneNumber) {
        mPhoneNumber = pPhoneNumber;
    }
}
