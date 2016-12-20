package rent.model.entities;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rent.application.managers.TableManager;
import rent.application.utils.ParseUtil;
import rent.application.utils.UiUtil;
import rent.interfaces.IEntity;
import rent.ui.entities.UiTariff;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Entity for Tariff model.
 *
 * @author zagart
 */
@Entity
@Table(name = "tariff", catalog = "rent")
public class Tariff implements IEntity<UiTariff> {
    public static final String MENU_NAME = "Тарифы";
    private Long mAmount;
    private List<Bill> mBillList = new ArrayList<>();
    private Date mDate;
    private Long mId;
    private String mName;

    public void addBill(final Bill pBill) {
        mBillList.add(pBill);
        pBill.setTariff(this);
    }

    @Override
    public List<String> createFieldsList() {
        return new ArrayList<String>() {
            {
                add(Fields.ID);
                add(Fields.NAME);
                add(Fields.AMOUNT);
                add(Fields.DATE);
            }
        };
    }

    @Override
    public Stage createModelEditStage() {
        return UiUtil.getStageByFields(Fields.ID, Fields.NAME, Fields.AMOUNT, Fields.DATE);
    }

    @Override
    public TableManager<UiTariff> createTableManager() {
        final ArrayList<PropertyValueFactory> factories = new ArrayList<PropertyValueFactory>() {
            {
                add(new PropertyValueFactory<UiTariff, String>(UiTariff.Fields.ID));
                add(new PropertyValueFactory<UiTariff, String>(UiTariff.Fields.NAME));
                add(new PropertyValueFactory<UiTariff, String>(UiTariff.Fields.AMOUNT));
                add(new PropertyValueFactory<UiTariff, String>(UiTariff.Fields.DATE));
            }
        };
        return new TableManager<>(factories, Fields.ID, Fields.NAME, Fields.AMOUNT, Fields.DATE);
    }

    @Override
    public UiTariff createTableModel() {
        return new UiTariff(this);
    }

    @Override
    public IEntity<UiTariff> extractEntityFromMap(final Map<String, String> pFields) {
        mId = Long.valueOf(pFields.get(Fields.ID));
        mName = pFields.get(Fields.NAME);
        mAmount = Long.valueOf(pFields.get(Fields.AMOUNT));
        mDate = ParseUtil.parseStringToDate(pFields.get(Fields.DATE));
        return this;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    public Tariff setId(Long pId) {
        mId = pId;
        return this;
    }

    @Column(name = "amount", nullable = false)
    public Long getAmount() {
        return mAmount;
    }

    public Tariff setAmount(Long pAmount) {
        mAmount = pAmount;
        return this;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Bill.class, mappedBy = "tariff")
    public List<Bill> getBillList() {
        return mBillList;
    }

    public Tariff setBillList(List<Bill> pBillList) {
        mBillList = pBillList;
        return this;
    }

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return mDate;
    }

    public Tariff setDate(Date pDate) {
        mDate = pDate;
        return this;
    }

    @Column(name = "name")
    public String getName() {
        return mName;
    }

    public Tariff setName(String pName) {
        mName = pName;
        return this;
    }

    private interface Fields {
        String AMOUNT = "Значение";
        String DATE = "Дата";
        String ID = "ID";
        String NAME = "Наименование";
    }
}
