package rent.ui.entities;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import rent.interfaces.ITableModel;
import rent.model.entities.Tariff;

/**
 * Table view model for {@link Tariff} entity.
 *
 * @author zagart
 */
public class UiTariff implements ITableModel<Long, Tariff> {
    final private Tariff mTariff;
    private SimpleLongProperty mAmount = new SimpleLongProperty();
    private SimpleStringProperty mDate = new SimpleStringProperty();
    private SimpleLongProperty mId = new SimpleLongProperty();
    private SimpleStringProperty mName = new SimpleStringProperty();

    public UiTariff(final Tariff pTariff) {
        mAmount.set(pTariff.getAmount());
        mDate.set(pTariff.getDate().toString());
        mId.set(pTariff.getId());
        mName.set(pTariff.getName());
        mTariff = pTariff;
    }

    @Override
    public Tariff extractEntity() {
        return mTariff;
    }

    @Override
    public Long getId() {
        return mId.get();
    }

    public void setId(long pId) {
        this.mId.set(pId);
    }

    public long getAmount() {
        return mAmount.get();
    }

    public void setAmount(long pAmount) {
        this.mAmount.set(pAmount);
    }

    public String getDate() {
        return mDate.get();
    }

    public void setDate(String pDate) {
        this.mDate.set(pDate);
    }

    public String getName() {
        return mName.get();
    }

    public void setName(String pName) {
        this.mName.set(pName);
    }

    @Override
    public int hashCode() {
        return mId.hashCode();
    }

    @Override
    public boolean equals(final Object pO) {
        if (this == pO) return true;
        if (pO == null || getClass() != pO.getClass()) return false;
        UiTariff tariff = (UiTariff) pO;
        return mId.equals(tariff.mId);
    }

    public interface Fields {
        String AMOUNT = "amount";
        String DATE = "date";
        String ID = "id";
        String NAME = "name";
    }
}
