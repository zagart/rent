package rent.model.entities;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rent.application.managers.TableManager;
import rent.application.singletons.Context;
import rent.application.utils.ParseUtil;
import rent.application.utils.UiUtil;
import rent.interfaces.IEntity;
import rent.ui.entities.UiPayment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Entity for Payment model.
 *
 * @author zagart
 */
@Entity
@Table(name = "payment", catalog = "rent")
public class Payment implements IEntity<UiPayment> {
    public static final String MENU_NAME = "Платежи";
    private Long mAmount;
    private Long mBacklog;
    private Customer mCustomer;
    private Long mId;
    private Date mTimestamp;

    @Override
    public List<String> createFieldsList() {
        return new ArrayList<String>() {
            {
                add(Fields.ID);
                add(Fields.CUSTOMER_ID);
                add(Fields.AMOUNT);
                add(Fields.BACKLOG);
                add(Fields.TIMESTAMP);
            }
        };
    }

    @Override
    public Stage createModelEditStage() {
        return UiUtil.getStageByFields(Fields.ID, Fields.CUSTOMER_ID, Fields.AMOUNT, Fields.BACKLOG, Fields.TIMESTAMP);
    }

    @Override
    public TableManager<UiPayment> createTableManager() {
        final ArrayList<PropertyValueFactory> factories = new ArrayList<PropertyValueFactory>() {
            {
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.ID));
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.CUSTOMER_ID));
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.AMOUNT));
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.BACKLOG));
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.TIMESTAMP));
            }
        };
        return new TableManager<>(
                factories,
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.AMOUNT,
                Fields.BACKLOG,
                Fields.TIMESTAMP);
    }

    @Override
    public UiPayment createTableModel() {
        return new UiPayment(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IEntity<UiPayment> extractEntityFromMap(final Map<String, String> pFields) {
        mId = Long.valueOf(pFields.get(Fields.ID));
        mCustomer = (Customer) Context
                .getService(Customer.class)
                .getById(Long.valueOf(pFields.get(Fields.CUSTOMER_ID)));
        mAmount = Long.valueOf(pFields.get(Fields.AMOUNT));
        mBacklog = Long.valueOf(pFields.get(Fields.BACKLOG));
        mTimestamp = ParseUtil.parseStringToDate(pFields.get(Fields.TIMESTAMP));
        return this;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    public Payment setId(Long pId) {
        mId = pId;
        return this;
    }

    @Column(name = "amount", nullable = false)
    public Long getAmount() {
        return mAmount;
    }

    public Payment setAmount(Long pAmount) {
        mAmount = pAmount;
        return this;
    }

    @Column(name = "backlog", nullable = false)
    public Long getBacklog() {
        return mBacklog;
    }

    public Payment setBacklog(Long pBacklog) {
        mBacklog = pBacklog;
        return this;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    public Customer getCustomer() {
        return mCustomer;
    }

    public Payment setCustomer(Customer pCustomer) {
        mCustomer = pCustomer;
        return this;
    }

    @Column(name = "timestamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimestamp() {
        return mTimestamp;
    }

    public Payment setTimestamp(Date pTimestamp) {
        mTimestamp = pTimestamp;
        return this;
    }

    public interface Fields {
        String AMOUNT = "Значение";
        String BACKLOG = "Задолженность";
        String CUSTOMER_ID = "ID заказчика";
        String ID = "ID";
        String TIMESTAMP = "Дата оплаты";
    }
}
