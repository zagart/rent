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
    private Long mCustomerId;
    private Long mId;
    private Date mTimestamp;

    @Column(name = "amount", nullable = false)
    public Long getAmount() {
        return mAmount;
    }

    public void setAmount(Long pAmount) {
        mAmount = pAmount;
    }

    @Column(name = "backlog", nullable = false)
    public Long getBacklog() {
        return mBacklog;
    }

    public void setBacklog(Long pBacklog) {
        mBacklog = pBacklog;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long pCustomerId) {
        mCustomerId = pCustomerId;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    public void setId(Long pId) {
        mId = pId;
    }

    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Date pTimestamp) {
        mTimestamp = pTimestamp;
    }
}
