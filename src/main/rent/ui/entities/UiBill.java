package rent.ui.entities;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import rent.interfaces.ITableModel;
import rent.model.entities.Bill;

/**
 * Table view model for {@link Bill} entity.
 *
 * @author zagart
 */
public class UiBill implements ITableModel<Long, Bill> {
    private Bill mBill;
    private SimpleStringProperty mBillDate = new SimpleStringProperty();
    private SimpleLongProperty mColdWaterBill = new SimpleLongProperty();
    private SimpleLongProperty mCustomerId = new SimpleLongProperty();
    private SimpleLongProperty mGazBill = new SimpleLongProperty();
    private SimpleLongProperty mHotWaterBill = new SimpleLongProperty();
    private SimpleLongProperty mId = new SimpleLongProperty();
    private SimpleLongProperty mLightBill = new SimpleLongProperty();
    private SimpleLongProperty mTariffId = new SimpleLongProperty();

    public UiBill(final Bill pBill) {
        if (pBill.getCustomer() != null && pBill.getTariff() != null) {
            if (pBill.getCustomer().getId() != null && pBill.getTariff().getId() != null) {
                mBillDate.set(pBill.getBillDate().toString());
                mColdWaterBill.set(pBill.getColdWaterBill());
                mCustomerId.set(pBill.getCustomer().getId());
                mGazBill.set(pBill.getGazBill());
                mHotWaterBill.set(pBill.getHotWaterBill());
                mId.set(pBill.getId());
                mLightBill.set(pBill.getLightBill());
                mTariffId.set(pBill.getTariff().getId());
                mBill = pBill;
            }
        }
    }

    @Override
    public boolean equals(final Object pO) {
        if (this == pO) return true;
        if (pO == null || getClass() != pO.getClass()) return false;
        UiBill uiBill = (UiBill) pO;
        if (!mCustomerId.equals(uiBill.mCustomerId)) return false;
        return mId.equals(uiBill.mId);
    }

    @Override
    public int hashCode() {
        int result = mCustomerId.hashCode();
        result = 31 * result + mId.hashCode();
        return result;
    }

    @Override
    public Bill extractEntity() {
        return mBill;
    }

    @Override
    public Long getId() {
        return mId.get();
    }

    public void setId(long pId) {
        this.mId.set(pId);
    }

    public SimpleLongProperty gazBillProperty() {
        return mGazBill;
    }

    public String getBillDate() {
        return mBillDate.get();
    }

    public void setBillDate(String pBillDate) {
        this.mBillDate.set(pBillDate);
    }

    public long getColdWaterBill() {
        return mColdWaterBill.get();
    }

    public void setColdWaterBill(long pColdWaterBill) {
        this.mColdWaterBill.set(pColdWaterBill);
    }

    public long getCustomerId() {
        return mCustomerId.get();
    }

    public void setCustomerId(long pCustomerId) {
        this.mCustomerId.set(pCustomerId);
    }

    public long getGazBill() {
        return mGazBill.get();
    }

    public void setGazBill(long pGazBill) {
        this.mGazBill.set(pGazBill);
    }

    public long getHotWaterBill() {
        return mHotWaterBill.get();
    }

    public void setHotWaterBill(long pHotWaterBill) {
        this.mHotWaterBill.set(pHotWaterBill);
    }

    public long getLightBill() {
        return mLightBill.get();
    }

    public void setLightBill(long pLightBill) {
        this.mLightBill.set(pLightBill);
    }

    public long getTariffId() {
        return mTariffId.get();
    }

    public void setTariffId(long pTariffId) {
        this.mTariffId.set(pTariffId);
    }

    public interface Fields {
        String COLD_WATER_BILL = "coldWaterBill";
        String CUSTOMER_ID = "customerId";
        String DATE = "billDate";
        String GAZ_BILL = "gazBill";
        String HOT_WATER_BILL = "hotWaterBill";
        String ID = "id";
        String LIGHT_BILL = "lightBill";
        String TARIFF_ID = "tariffId";
    }
}
