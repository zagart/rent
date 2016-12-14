package rent.model;

import rent.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for Bill model.
 *
 * @author zagart
 */
@Entity
@Table(name = "bill", catalog = "rent")
public class Bill implements IEntity {
    private Date mBillDate;
    private Long mColdWaterBill;
    private Long mCustomerId;
    private Long mGazBill;
    private Long mHotWaterBill;
    private Long mId;
    private Long mLightBill;
    private Long mTariffId;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    public Date getBillDate() {
        return mBillDate;
    }

    public void setBillDate(Date pBillDate) {
        mBillDate = pBillDate;
    }

    @Column(name = "cold_water", nullable = false)
    public Long getColdWaterBill() {
        return mColdWaterBill;
    }

    public void setColdWaterBill(Long pColdWaterBill) {
        mColdWaterBill = pColdWaterBill;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long pCustomerId) {
        mCustomerId = pCustomerId;
    }

    @Column(name = "gaz", nullable = false)
    public Long getGazBill() {
        return mGazBill;
    }

    public void setGazBill(Long pGazBill) {
        mGazBill = pGazBill;
    }

    @Column(name = "hot_water", nullable = false)
    public Long getHotWaterBill() {
        return mHotWaterBill;
    }

    public void setHotWaterBill(Long pHotWaterBill) {
        mHotWaterBill = pHotWaterBill;
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

    @Column(name = "light", nullable = false)
    public Long getLightBill() {
        return mLightBill;
    }

    public void setLightBill(Long pLightBill) {
        mLightBill = pLightBill;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tariff.class)
    public Long getTariffId() {
        return mTariffId;
    }

    public void setTariffId(Long pTariffId) {
        mTariffId = pTariffId;
    }
}
