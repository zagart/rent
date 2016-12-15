package rent.model.entities;

import rent.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for Expense model.
 *
 * @author zagart
 */
@Entity
@Table(name = "expense", catalog = "rent")
public class Expense implements IEntity {
    private Long mColdWaterExpense;
    private Long mCustomerId;
    private Date mExpenseDate;
    private Long mGazExpense;
    private Long mHotWaterExpense;
    private Long mId;
    private Long mLightExpense;

    @Column(name = "cold_water", nullable = false)
    public Long getColdWaterExpense() {
        return mColdWaterExpense;
    }

    public void setColdWaterExpense(Long pColdWaterExpense) {
        mColdWaterExpense = pColdWaterExpense;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long pCustomerId) {
        mCustomerId = pCustomerId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    public Date getExpenseDate() {
        return mExpenseDate;
    }

    public void setExpenseDate(Date pExpenseDate) {
        mExpenseDate = pExpenseDate;
    }

    @Column(name = "gaz", nullable = false)
    public Long getGazExpense() {
        return mGazExpense;
    }

    public void setGazExpense(Long pGazExpense) {
        mGazExpense = pGazExpense;
    }

    @Column(name = "hot_water", nullable = false)
    public Long getHotWaterExpense() {
        return mHotWaterExpense;
    }

    public void setHotWaterExpense(Long pHotWaterExpense) {
        mHotWaterExpense = pHotWaterExpense;
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
    public Long getLightExpense() {
        return mLightExpense;
    }

    public void setLightExpense(Long pLightExpense) {
        mLightExpense = pLightExpense;
    }
}
