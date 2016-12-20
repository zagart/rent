package rent.model.entities;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rent.application.managers.TableManager;
import rent.application.singletons.Context;
import rent.application.utils.ParseUtil;
import rent.application.utils.UiUtil;
import rent.interfaces.IEntity;
import rent.ui.entities.UiPassport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<String> createFieldsList() {
        return new ArrayList<String>() {
            {
                add(Fields.ID);
                add(Fields.CUSTOMER_ID);
                add(Fields.PERSONAL_NUMBER);
                add(Fields.DATE_OF_ISSUE);
                add(Fields.EXPIRATION_DATE);
                add(Fields.ISSUED_BY);
            }
        };
    }

    @Override
    public Stage createModelEditStage() {
        return UiUtil.getStageByFields(
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.PERSONAL_NUMBER,
                Fields.DATE_OF_ISSUE,
                Fields.EXPIRATION_DATE,
                Fields.ISSUED_BY);
    }

    @Override
    public TableManager<UiPassport> createTableManager() {
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
        return new TableManager<>(
                factories,
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.PERSONAL_NUMBER,
                Fields.DATE_OF_ISSUE,
                Fields.EXPIRATION_DATE,
                Fields.ISSUED_BY);
    }

    @Override
    public UiPassport createTableModel() {
        return new UiPassport(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IEntity<UiPassport> extractEntityFromMap(final Map<String, String> pFields) {
        mId = Long.valueOf(pFields.get(Fields.ID));
        mCustomer = (Customer) Context
                .getService(Customer.class)
                .getById(Long.valueOf(pFields.get(Fields.CUSTOMER_ID)));
        mPersonalNumber = pFields.get(Fields.PERSONAL_NUMBER);
        mDateOfIssue = ParseUtil.parseStringToDate(pFields.get(Fields.DATE_OF_ISSUE));
        mExpirationDate = ParseUtil.parseStringToDate(pFields.get(Fields.EXPIRATION_DATE));
        mIssuedBy = pFields.get(Fields.ISSUED_BY);
        return this;
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
