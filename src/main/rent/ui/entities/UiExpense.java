package rent.ui.entities;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import rent.interfaces.ITableModel;
import rent.model.entities.Customer;
import rent.model.entities.Expense;

/**
 * Table view model for {@link Expense} entity.
 *
 * @author zagart
 */
public class UiExpense implements ITableModel<Long, Expense> {
    final private Expense mExpense;
    private SimpleLongProperty mColdWaterExpense = new SimpleLongProperty();
    private SimpleLongProperty mCustomerId = new SimpleLongProperty();
    private SimpleStringProperty mExpenseDate = new SimpleStringProperty();
    private SimpleLongProperty mGazExpense = new SimpleLongProperty();
    private SimpleLongProperty mHotWaterExpense = new SimpleLongProperty();
    private SimpleLongProperty mId = new SimpleLongProperty();
    private SimpleLongProperty mLightExpense = new SimpleLongProperty();

    public UiExpense(final Expense pExpense) {
        final Customer customer = pExpense.getCustomer();
        if (customer != null) {
            mColdWaterExpense.set(pExpense.getColdWaterExpense());
            mCustomerId.set(customer.getId());
            mExpenseDate.set(pExpense.getExpenseDate().toString());
            mGazExpense.set(pExpense.getGazExpense());
            mHotWaterExpense.set(pExpense.getHotWaterExpense());
            mId.set(pExpense.getId());
            mLightExpense.set(pExpense.getLightExpense());
            mExpense = pExpense;
        } else {
            mExpense = null;
        }
    }

    @Override
    public Expense extractEntity() {
        return mExpense;
    }

    @Override
    public Long getId() {
        return mId.get();
    }

    public void setId(long pId) {
        this.mId.set(pId);
    }

    public long getColdWaterExpense() {
        return mColdWaterExpense.get();
    }

    public void setColdWaterExpense(long pColdWaterExpense) {
        this.mColdWaterExpense.set(pColdWaterExpense);
    }

    public long getCustomerId() {
        return mCustomerId.get();
    }

    public void setCustomerId(long pCustomerId) {
        this.mCustomerId.set(pCustomerId);
    }

    public String getExpenseDate() {
        return mExpenseDate.get();
    }

    public void setExpenseDate(String pExpenseDate) {
        this.mExpenseDate.set(pExpenseDate);
    }

    public long getGazExpense() {
        return mGazExpense.get();
    }

    public void setGazExpense(long pGazExpense) {
        this.mGazExpense.set(pGazExpense);
    }

    public long getHotWaterExpense() {
        return mHotWaterExpense.get();
    }

    public void setHotWaterExpense(long pHotWaterExpense) {
        this.mHotWaterExpense.set(pHotWaterExpense);
    }

    public long getLightExpense() {
        return mLightExpense.get();
    }

    public void setLightExpense(long pLightExpense) {
        this.mLightExpense.set(pLightExpense);
    }

    @Override
    public int hashCode() {
        int result = mCustomerId.hashCode();
        result = 31 * result + mId.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object pO) {
        if (this == pO) return true;
        if (pO == null || getClass() != pO.getClass()) return false;
        UiExpense uiExpense = (UiExpense) pO;
        if (!mCustomerId.equals(uiExpense.mCustomerId)) return false;
        return mId.equals(uiExpense.mId);
    }

    public interface Fields {
        String COLD_WATER_EXPENSE = "coldWaterExpense";
        String CUSTOMER_ID = "customerId";
        String DATE = "expenseDate";
        String GAZ_EXPENSE = "gazExpense";
        String HOT_WATER_EXPENSE = "hotWaterExpense";
        String ID = "id";
        String LIGHT_EXPENSE = "lightExpense";
    }
}
