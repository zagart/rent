package rent.model.entities;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rent.application.utils.JavaFxUtil;
import rent.interfaces.IEntity;
import rent.ui.entities.UiPassport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Entity for Passport model.
 *
 * @author zagart
 */
@Entity
@Table(name = "passport", catalog = "rent")
public class Passport implements IEntity<UiPassport> {
    public static final String MENU_NAME = "Паспорта";
    private Customer mCustomer;
    private Date mDateOfIssue;
    private Date mExpirationDate;
    private Long mId;
    private String mIssuedBy;
    private String mPersonalNumber;

    @Override
    public UiPassport createTableModel() {
        return new UiPassport(this);
    }

    @Override
    public TableView<UiPassport> createTableView() {
        final ArrayList<PropertyValueFactory> factories = new ArrayList<PropertyValueFactory>() {
            {
                add(new PropertyValueFactory<UiPassport, String>(UiPassport.Fields.ID));
                add(new PropertyValueFactory<UiPassport, String>(UiPassport.Fields.CUSTOMER_ID));
                add(new PropertyValueFactory<UiPassport, String>(UiPassport.Fields.PERSONAL_NUMBER));
                add(new PropertyValueFactory<UiPassport, String>(UiPassport.Fields.DATE_OF_ISSUE));
                add(new PropertyValueFactory<UiPassport, String>(UiPassport.Fields.EXPIRATION_DATE));
                add(new PropertyValueFactory<UiPassport, String>(UiPassport.Fields.ISSUED_BY));
            }
        };
        return JavaFxUtil.createTable(
                factories,
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.PERSONAL_NUMBER,
                Fields.DATE_OF_ISSUE,
                Fields.EXPIRATION_DATE,
                Fields.ISSUED_BY);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return mId;
    }

    public Passport setId(Long pId) {
        mId = pId;
        return this;
    }

    @OneToOne(targetEntity = Customer.class)
    public Customer getCustomer() {
        return mCustomer;
    }

    public void setCustomer(Customer pCustomer) {
        mCustomer = pCustomer;
    }

    @Column(name = "date_of_issue", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDateOfIssue() {
        return mDateOfIssue;
    }

    public Passport setDateOfIssue(Date pDateOfIssue) {
        mDateOfIssue = pDateOfIssue;
        return this;
    }

    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getExpirationDate() {
        return mExpirationDate;
    }

    public Passport setExpirationDate(Date pExpirationDate) {
        mExpirationDate = pExpirationDate;
        return this;
    }

    @Column(name = "issued_by", nullable = false)
    public String getIssuedBy() {
        return mIssuedBy;
    }

    public Passport setIssuedBy(String pIssuedBy) {
        mIssuedBy = pIssuedBy;
        return this;
    }

    @Column(name = "personal_number", nullable = false)
    public String getPersonalNumber() {
        return mPersonalNumber;
    }

    public Passport setPersonalNumber(String pPersonalNumber) {
        mPersonalNumber = pPersonalNumber;
        return this;
    }

    private interface Fields {
        String CUSTOMER_ID = "ID заказчика";
        String DATE_OF_ISSUE = "Дата выдачи";
        String EXPIRATION_DATE = "Дата истечения";
        String ID = "ID";
        String ISSUED_BY = "Выдан";
        String PERSONAL_NUMBER = "Личный номер";
    }
}
