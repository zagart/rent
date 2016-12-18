package rent.ui.entities;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import rent.interfaces.ITableModel;
import rent.model.entities.Customer;

/**
 * Table view model for {@link Customer} entity.
 *
 * @author zagart
 */
public class UiCustomer implements ITableModel<Long> {
    private SimpleStringProperty mAddress = new SimpleStringProperty();
    private SimpleStringProperty mFirstName = new SimpleStringProperty();
    private SimpleLongProperty mId = new SimpleLongProperty();
    private SimpleStringProperty mLastName = new SimpleStringProperty();
    private SimpleStringProperty mPatronymic = new SimpleStringProperty();
    private SimpleStringProperty mPhoneNumber = new SimpleStringProperty();

    public UiCustomer(final Customer pCustomer) {
        mAddress.set(pCustomer.getAddress());
        mFirstName.set(pCustomer.getFirstName());
        mId.set(pCustomer.getId());
        mLastName.set(pCustomer.getLastName());
        mPatronymic.set(pCustomer.getPatronymic());
        mPhoneNumber.set(pCustomer.getPhoneNumber());
    }

    public String getAddress() {
        return mAddress.get();
    }

    public void setAddress(String pAddress) {
        this.mAddress.set(pAddress);
    }

    public String getFirstName() {
        return mFirstName.get();
    }

    public void setFirstName(String pFirstName) {
        this.mFirstName.set(pFirstName);
    }

    @Override
    public Long getId() {
        return this.mId.get();
    }

    public void setId(long pId) {
        this.mId.set(pId);
    }

    public String getLastName() {
        return mLastName.get();
    }

    public void setLastName(String pLastName) {
        this.mLastName.set(pLastName);
    }

    public String getPatronymic() {
        return mPatronymic.get();
    }

    public void setPatronymic(String pPatronymic) {
        this.mPatronymic.set(pPatronymic);
    }

    public String getPhoneNumber() {
        return mPhoneNumber.get();
    }

    public void setPhoneNumber(String pPhoneNumber) {
        this.mPhoneNumber.set(pPhoneNumber);
    }

    public interface Fields {
        String ADDRESS = "address";
        String FIRST_NAME = "firstName";
        String ID = "id";
        String LAST_NAME = "lastName";
        String PATRONYMIC = "patronymic";
        String PHONE_NUMBER = "phoneNumber";
    }
}
