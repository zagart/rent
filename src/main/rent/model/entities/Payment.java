package rent.model.entities;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import rent.application.utils.JavaFxUtil;
import rent.interfaces.IEntity;
import rent.ui.entities.UiPayment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

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
    public UiPayment createTableModel() {
        return new UiPayment(this);
    }

    @Override
    public TableView<UiPayment> createTableView() {
        final ArrayList<PropertyValueFactory> factories = new ArrayList<PropertyValueFactory>() {
            {
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.ID));
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.CUSTOMER_ID));
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.AMOUNT));
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.BACKLOG));
                add(new PropertyValueFactory<UiPayment, String>(UiPayment.Fields.TIMESTAMP));
            }
        };
        return JavaFxUtil.createTable(
                factories,
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.AMOUNT,
                Fields.BACKLOG,
                Fields.TIMESTAMP);
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
