package rent.model.entities;

import javafx.scene.control.TableView;
import rent.application.utils.JavaFxUtil;
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
    private Customer mCustomer;
    private Date mExpenseDate;
    private Long mGazExpense;
    private Long mHotWaterExpense;
    private Long mId;
    private Long mLightExpense;

    @Column(name = "cold_water", nullable = false)
    public Long getColdWaterExpense() {
        return mColdWaterExpense;
    }

    public Expense setColdWaterExpense(Long pColdWaterExpense) {
        mColdWaterExpense = pColdWaterExpense;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    public Customer getCustomer() {
        return mCustomer;
    }

    public Expense setCustomer(Customer pCustomer) {
        mCustomer = pCustomer;
        return this;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    public Date getExpenseDate() {
        return mExpenseDate;
    }

    public Expense setExpenseDate(Date pExpenseDate) {
        mExpenseDate = pExpenseDate;
        return this;
    }

    @Column(name = "gaz", nullable = false)
    public Long getGazExpense() {
        return mGazExpense;
    }

    public Expense setGazExpense(Long pGazExpense) {
        mGazExpense = pGazExpense;
        return this;
    }

    @Column(name = "hot_water", nullable = false)
    public Long getHotWaterExpense() {
        return mHotWaterExpense;
    }

    public Expense setHotWaterExpense(Long pHotWaterExpense) {
        mHotWaterExpense = pHotWaterExpense;
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    @Override
    public TableView getTableView() {
        return JavaFxUtil.createTable(
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.LIGHT_EXPENSE,
                Fields.GAZ_EXPENSE,
                Fields.COLD_WATER_EXPENSE,
                Fields.HOT_WATER_EXPENSE,
                Fields.DATE);
    }

    public Expense setId(Long pId) {
        mId = pId;
        return this;
    }

    @Column(name = "light", nullable = false)
    public Long getLightExpense() {
        return mLightExpense;
    }

    public Expense setLightExpense(Long pLightExpense) {
        mLightExpense = pLightExpense;
        return this;
    }

    private interface Fields {
        String COLD_WATER_EXPENSE = "Холодная вода";
        String CUSTOMER_ID = "ID заказчика";
        String DATE = "Дата";
        String GAZ_EXPENSE = "Газ";
        String HOT_WATER_EXPENSE = "Горячая вода";
        String ID = "ID";
        String LIGHT_EXPENSE = "Свет";
    }
}
