package rent.model;

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
    private Date mDateOfIssue;
    private Date mExpirationDate;
    private String mId;
    private String mIssuedBy;

    @Column(name = "date_of_issue", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDateOfIssue() {
        return mDateOfIssue;
    }

    public void setDateOfIssue(Date pDateOfIssue) {
        mDateOfIssue = pDateOfIssue;
    }

    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getExpirationDate() {
        return mExpirationDate;
    }

    public void setExpirationDate(Date pExpirationDate) {
        mExpirationDate = pExpirationDate;
    }

    @Id
    @OneToOne(targetEntity = Customer.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return mId;
    }

    public void setId(String pId) {
        mId = pId;
    }

    @Column(name = "issued_by", nullable = false)
    public String getIssuedBy() {
        return mIssuedBy;
    }

    public void setIssuedBy(String pIssuedBy) {
        mIssuedBy = pIssuedBy;
    }
}
