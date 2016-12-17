package rent.model.entities;

import javafx.scene.control.TableView;
import rent.application.utils.JavaFxUtil;
import rent.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for Passport model.
 *
 * @author zagart
 */
@Entity
@Table(name = "passport", catalog = "rent")
public class Passport implements IEntity {
    private Customer mCustomer;
    private Date mDateOfIssue;
    private Date mExpirationDate;
    private Long mId;
    private String mIssuedBy;
    private String mPersonalNumber;

    @OneToOne(targetEntity = Customer.class)
    public Customer getCustomer() {
        return mCustomer;
    }

    public void setCustomer(Customer pCustomer) {
        mCustomer = pCustomer;
    }

    @Column(name = "date_of_issue", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDateOfIssue() {
        return mDateOfIssue;
    }

    public Passport setDateOfIssue(Date pDateOfIssue) {
        mDateOfIssue = pDateOfIssue;
        return this;
    }

    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getExpirationDate() {
        return mExpirationDate;
    }

    public Passport setExpirationDate(Date pExpirationDate) {
        mExpirationDate = pExpirationDate;
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return mId;
    }

    @Override
    public TableView getTableView() {
        return JavaFxUtil.createTable(
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.DATE_OF_ISSUE,
                Fields.EXPIRATION_DATE,
                Fields.ISSUED_BY);
    }

    public Passport setId(Long pId) {
        mId = pId;
        return this;
    }

    @Column(name = "issued_by", nullable = false)
    public String getIssuedBy() {
        return mIssuedBy;
    }

    public Passport setIssuedBy(String pIssuedBy) {
        mIssuedBy = pIssuedBy;
        return this;
    }

    @Column(name = "personal_number", nullable = false)
    public String getPersonalNumber() {
        return mPersonalNumber;
    }

    public Passport setPersonalNumber(String pPersonalNumber) {
        mPersonalNumber = pPersonalNumber;
        return this;
    }

    private interface Fields {
        String CUSTOMER_ID = "ID заказчика";
        String DATE_OF_ISSUE = "Дата выдачи";
        String EXPIRATION_DATE = "Дата истечения";
        String ID = "ID";
        String ISSUED_BY = "Выдан";
    }
}
