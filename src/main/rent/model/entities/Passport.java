package rent.model.entities;

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
}
