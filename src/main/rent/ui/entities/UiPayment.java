package rent.ui.entities;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import rent.interfaces.ITableModel;
import rent.model.entities.Customer;
import rent.model.entities.Payment;

/**
 * Table view model for {@link Payment} entity.
 *
 * @author zagart
 */
public class UiPayment implements ITableModel<Long, Payment> {
    final private Payment mPayment;
    private SimpleLongProperty mAmount = new SimpleLongProperty();
    private SimpleLongProperty mBacklog = new SimpleLongProperty();
    private SimpleLongProperty mCustomerId = new SimpleLongProperty();
    private SimpleLongProperty mId = new SimpleLongProperty();
    private SimpleStringProperty mTimestamp = new SimpleStringProperty();

    public UiPayment(final Payment pPayment) {
        final Customer customer = pPayment.getCustomer();
        if (customer != null) {
            mAmount.set(pPayment.getAmount());
            mBacklog.set(pPayment.getBacklog());
            mCustomerId.set(customer.getId());
            mId.set(pPayment.getId());
            mTimestamp.set(pPayment.getTimestamp().toString());
            mPayment = pPayment;
        } else {
            mPayment = null;
        }
    }

    @Override
    public Payment extractEntity() {
        return mPayment;
    }

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (pO == null || getClass() != pO.getClass()) return false;
        UiPayment uiPayment = (UiPayment) pO;
        if (!mCustomerId.equals(uiPayment.mCustomerId)) return false;
        return mId.equals(uiPayment.mId);
    }

    @Override
    public int hashCode() {
        int result = mCustomerId.hashCode();
        result = 31 * result + mId.hashCode();
        return result;
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

    public long getBacklog() {
        return mBacklog.get();
    }

    public void setBacklog(long pBacklog) {
        this.mBacklog.set(pBacklog);
    }

    public long getCustomerId() {
        return mCustomerId.get();
    }

    public void setCustomerId(long pCustomerId) {
        this.mCustomerId.set(pCustomerId);
    }

    public String getTimestamp() {
        return mTimestamp.get();
    }

    public void setTimestamp(String pTimestamp) {
        this.mTimestamp.set(pTimestamp);
    }

    public interface Fields {
        String AMOUNT = "amount";
        String BACKLOG = "backlog";
        String CUSTOMER_ID = "customerId";
        String ID = "id";
        String TIMESTAMP = "timestamp";
    }
}
