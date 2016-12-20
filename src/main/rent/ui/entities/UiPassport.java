package rent.ui.entities;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import rent.interfaces.ITableModel;
import rent.model.entities.Customer;
import rent.model.entities.Passport;

/**
 * Table view model for {@link Passport} entity.
 *
 * @author zagart
 */
public class UiPassport implements ITableModel<Long, Passport> {
    final private Passport mPassport;
    private SimpleLongProperty mCustomerId = new SimpleLongProperty();
    private SimpleStringProperty mDateOfIssue = new SimpleStringProperty();
    private SimpleStringProperty mExpirationDate = new SimpleStringProperty();
    private SimpleLongProperty mId = new SimpleLongProperty();
    private SimpleStringProperty mIssuedBy = new SimpleStringProperty();
    private SimpleStringProperty mPersonalNumber = new SimpleStringProperty();

    public UiPassport(final Passport pPassport) {
        final Customer customer = pPassport.getCustomer();
        if (customer != null) {
            mCustomerId.set(customer.getId());
            mDateOfIssue.set(pPassport.getDateOfIssue().toString());
            mExpirationDate.set(pPassport.getExpirationDate().toString());
            mId.set(pPassport.getId());
            mIssuedBy.set(pPassport.getIssuedBy());
            mPersonalNumber.set(pPassport.getPersonalNumber());
            mPassport = pPassport;
        } else {
            mPassport = null;
        }
    }

    @Override
    public Passport extractEntity() {
        return mPassport;
    }

    @Override
    public Long getId() {
        return mId.get();
    }

    public void setId(long pId) {
        this.mId.set(pId);
    }

    public long getCustomerId() {
        return mCustomerId.get();
    }

    public void setCustomerId(long pCustomerId) {
        this.mCustomerId.set(pCustomerId);
    }

    public String getDateOfIssue() {
        return mDateOfIssue.get();
    }

    public void setDateOfIssue(String pDateOfIssue) {
        this.mDateOfIssue.set(pDateOfIssue);
    }

    @Override
    public boolean equals(final Object pO) {
        if (this == pO) return true;
        if (pO == null || getClass() != pO.getClass()) return false;
        UiPassport that = (UiPassport) pO;
        return mId.equals(that.mId);
    }

    @Override
    public int hashCode() {
        return mId.hashCode();
    }

    public String getExpirationDate() {
        return mExpirationDate.get();
    }

    public void setExpirationDate(String pExpirationDate) {
        this.mExpirationDate.set(pExpirationDate);
    }

    public String getIssuedBy() {
        return mIssuedBy.get();
    }

    public void setIssuedBy(String pIssuedBy) {
        this.mIssuedBy.set(pIssuedBy);
    }

    public String getPersonalNumber() {
        return mPersonalNumber.get();
    }

    public void setPersonalNumber(String pPersonalNumber) {
        this.mPersonalNumber.set(pPersonalNumber);
    }

    public interface Fields {
        String CUSTOMER_ID = "customerId";
        String DATE_OF_ISSUE = "dateOfIssue";
        String EXPIRATION_DATE = "expirationDate";
        String ID = "id";
        String ISSUED_BY = "issuedBy";
        String PERSONAL_NUMBER = "personalNumber";
    }
}
