package rent.model.entities;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rent.application.managers.TableManager;
import rent.application.singletons.Context;
import rent.application.utils.ParseUtil;
import rent.application.utils.UiUtil;
import rent.interfaces.IEntity;
import rent.ui.entities.UiExpense;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Entity for Expense model.
 *
 * @author zagart
 */
@Entity
@Table(name = "expense", catalog = "rent")
public class Expense implements IEntity<UiExpense> {
    public static final String MENU_NAME = "Показания";
    private Long mColdWaterExpense;
    private Customer mCustomer;
    private Date mExpenseDate;
    private Long mGazExpense;
    private Long mHotWaterExpense;
    private Long mId;
    private Long mLightExpense;

    @Override
    public List<String> createFieldsList() {
        return new ArrayList<String>() {
            {
                add(Fields.ID);
                add(Fields.CUSTOMER_ID);
                add(Fields.LIGHT_EXPENSE);
                add(Fields.GAZ_EXPENSE);
                add(Fields.COLD_WATER_EXPENSE);
                add(Fields.HOT_WATER_EXPENSE);
                add(Fields.DATE);
            }
        };
    }

    @Override
    public Stage createModelEditStage() {
        return UiUtil.getStageByFields(
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.LIGHT_EXPENSE,
                Fields.GAZ_EXPENSE,
                Fields.COLD_WATER_EXPENSE,
                Fields.HOT_WATER_EXPENSE,
                Fields.DATE);
    }

    @Override
    public TableManager<UiExpense> createTableManager() {
        final ArrayList<PropertyValueFactory> factories = new ArrayList<PropertyValueFactory>() {
            {
                add(new PropertyValueFactory<UiExpense, String>(UiExpense.Fields.ID));
                add(new PropertyValueFactory<UiExpense, String>(UiExpense.Fields.CUSTOMER_ID));
                add(new PropertyValueFactory<UiExpense, String>(UiExpense.Fields.LIGHT_EXPENSE));
                add(new PropertyValueFactory<UiExpense, String>(UiExpense.Fields.GAZ_EXPENSE));
                add(new PropertyValueFactory<UiExpense, String>(UiExpense.Fields.COLD_WATER_EXPENSE));
                add(new PropertyValueFactory<UiExpense, String>(UiExpense.Fields.HOT_WATER_EXPENSE));
                add(new PropertyValueFactory<UiExpense, String>(UiExpense.Fields.DATE));
            }
        };
        return new TableManager<>(
                factories,
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.LIGHT_EXPENSE,
                Fields.GAZ_EXPENSE,
                Fields.COLD_WATER_EXPENSE,
                Fields.HOT_WATER_EXPENSE,
                Fields.DATE);
    }

    @Override
    public UiExpense createTableModel() {
        return new UiExpense(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IEntity<UiExpense> extractEntityFromMap(final Map<String, String> pFields) {
        mId = Long.valueOf(pFields.get(Fields.ID));
        mCustomer = (Customer) Context
                .getService(Customer.class)
                .getById(Long.valueOf(pFields.get(Fields.CUSTOMER_ID)));
        mLightExpense = Long.valueOf(pFields.get(Fields.LIGHT_EXPENSE));
        mGazExpense = Long.valueOf(pFields.get(Fields.GAZ_EXPENSE));
        mColdWaterExpense = Long.valueOf(pFields.get(Fields.COLD_WATER_EXPENSE));
        mHotWaterExpense = Long.valueOf(pFields.get(Fields.HOT_WATER_EXPENSE));
        mExpenseDate = ParseUtil.parseStringToDate(pFields.get(Fields.DATE));
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    public Expense setId(Long pId) {
        mId = pId;
        return this;
    }

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
