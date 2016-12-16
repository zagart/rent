package rent.model.entities;

import rent.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for Payment model.
 *
 * @author zagart
 */
@Entity
@Table(name = "payment", catalog = "rent")
public class Payment implements IEntity {
    private Long mAmount;
    private Long mBacklog;
    private Customer mCustomer;
    private Long mId;
    private Date mTimestamp;

    @Column(name = "amount", nullable = false)
    public Long getAmount() {
        return mAmount;
    }

    public Payment setAmount(Long pAmount) {
        mAmount = pAmount;
        return this;
    }

    @Column(name = "backlog", nullable = false)
    public Long getBacklog() {
        return mBacklog;
    }

    public Payment setBacklog(Long pBacklog) {
        mBacklog = pBacklog;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    public Customer getCustomer() {
        return mCustomer;
    }

    public Payment setCustomer(Customer pCustomer) {
        mCustomer = pCustomer;
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    public Payment setId(Long pId) {
        mId = pId;
        return this;
    }

    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimestamp() {
        return mTimestamp;
    }

    public Payment setTimestamp(Date pTimestamp) {
        mTimestamp = pTimestamp;
        return this;
    }
}
