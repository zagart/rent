package rent.model.entities;

import rent.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity for Tariff model.
 *
 * @author zagart
 */
@Entity
@Table(name = "tariff", catalog = "rent")
public class Tariff implements IEntity {
    private Long mAmount;
    private List<Bill> mBillList;
    private Date mDate;
    private Long mId;
    private String mName;

    @Column(name = "amount", nullable = false)
    public Long getAmount() {
        return mAmount;
    }

    public void setAmount(Long pAmount) {
        mAmount = pAmount;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Bill.class, mappedBy = "tariffId")
    public List<Bill> getBillList() {
        return mBillList;
    }

    public void setBillList(List<Bill> pBillList) {
        mBillList = pBillList;
    }

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return mDate;
    }

    public void setDate(Date pDate) {
        mDate = pDate;
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

    @Column(name = "name")
    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }
}
