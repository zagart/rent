package rent.model.entities;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import rent.application.managers.TableManager;
import rent.application.singletons.Context;
import rent.application.utils.ParseUtil;
import rent.application.utils.UiUtil;
import rent.interfaces.IEntity;
import rent.ui.entities.UiBill;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Entity for Bill model.
 *
 * @author zagart
 */
@Entity
@Table(name = "bill", catalog = "rent")
public class Bill implements IEntity<UiBill> {
    public static final String MENU_NAME = "Начисления";
    private Date mBillDate;
    private Long mColdWaterBill;
    private Customer mCustomer;
    private Long mGazBill;
    private Long mHotWaterBill;
    private Long mId;
    private Long mLightBill;
    private Tariff mTariff;

    @Override
    public List<String> createFieldsList() {
        return new ArrayList<String>() {
            {
                add(Fields.ID);
                add(Fields.CUSTOMER_ID);
                add(Fields.TARIFF_ID);
                add(Fields.LIGHT_BILL);
                add(Fields.GAZ_BILL);
                add(Fields.GAZ_BILL);
                add(Fields.COLD_WATER_BILL);
                add(Fields.HOT_WATER_BILL);
                add(Fields.DATE);
            }
        };
    }

    @Override
    public Stage createModelEditStage() {
        return UiUtil.getStageByFields(
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.TARIFF_ID,
                Fields.LIGHT_BILL,
                Fields.GAZ_BILL,
                Fields.COLD_WATER_BILL,
                Fields.HOT_WATER_BILL,
                Fields.DATE);
    }

    @Override
    public TableManager<UiBill> createTableManager() {
        final ArrayList<PropertyValueFactory> factories = new ArrayList<PropertyValueFactory>() {
            {
                add(new PropertyValueFactory<UiBill, String>(UiBill.Fields.ID));
                add(new PropertyValueFactory<UiBill, String>(UiBill.Fields.CUSTOMER_ID));
                add(new PropertyValueFactory<UiBill, String>(UiBill.Fields.TARIFF_ID));
                add(new PropertyValueFactory<UiBill, String>(UiBill.Fields.LIGHT_BILL));
                add(new PropertyValueFactory<UiBill, String>(UiBill.Fields.GAZ_BILL));
                add(new PropertyValueFactory<UiBill, String>(UiBill.Fields.COLD_WATER_BILL));
                add(new PropertyValueFactory<UiBill, String>(UiBill.Fields.HOT_WATER_BILL));
                add(new PropertyValueFactory<UiBill, String>(UiBill.Fields.DATE));
            }
        };
        return new TableManager<>(
                factories,
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.TARIFF_ID,
                Fields.LIGHT_BILL,
                Fields.GAZ_BILL,
                Fields.COLD_WATER_BILL,
                Fields.HOT_WATER_BILL,
                Fields.DATE);
    }

    @Override
    public UiBill createTableModel() {
        return new UiBill(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IEntity<UiBill> extractEntityFromMap(final Map<String, String> pFields) {
        mId = Long.valueOf(pFields.get(Fields.ID));
        mCustomer = (Customer) Context
                .getService(Customer.class)
                .getById(Long.valueOf(pFields.get(Fields.CUSTOMER_ID)));
        mTariff = (Tariff) Context.getService(Tariff.class).getById(Long.valueOf(pFields.get(Fields.TARIFF_ID)));
        mLightBill = Long.valueOf(pFields.get(Fields.LIGHT_BILL));
        mGazBill = Long.valueOf(pFields.get(Fields.GAZ_BILL));
        mHotWaterBill = Long.valueOf(pFields.get(Fields.HOT_WATER_BILL));
        mBillDate = ParseUtil.parseStringToDate(pFields.get(Fields.DATE));
        return this;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    public Bill setId(Long pId) {
        mId = pId;
        return this;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    public Date getBillDate() {
        return mBillDate;
    }

    public Bill setBillDate(Date pBillDate) {
        mBillDate = pBillDate;
        return this;
    }

    @Column(name = "cold_water", nullable = false)
    public Long getColdWaterBill() {
        return mColdWaterBill;
    }

    public Bill setColdWaterBill(Long pColdWaterBill) {
        mColdWaterBill = pColdWaterBill;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return mCustomer;
    }

    public Bill setCustomer(Customer pCustomer) {
        mCustomer = pCustomer;
        return this;
    }

    @Column(name = "gaz", nullable = false)
    public Long getGazBill() {
        return mGazBill;
    }

    public Bill setGazBill(Long pGazBill) {
        mGazBill = pGazBill;
        return this;
    }

    @Column(name = "hot_water", nullable = false)
    public Long getHotWaterBill() {
        return mHotWaterBill;
    }

    public Bill setHotWaterBill(Long pHotWaterBill) {
        mHotWaterBill = pHotWaterBill;
        return this;
    }

    @Column(name = "light", nullable = false)
    public Long getLightBill() {
        return mLightBill;
    }

    public Bill setLightBill(Long pLightBill) {
        mLightBill = pLightBill;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Tariff.class)
    @JoinColumn(name = "tariff_id")
    public Tariff getTariff() {
        return mTariff;
    }

    public Bill setTariff(Tariff pTariff) {
        mTariff = pTariff;
        return this;
    }

    private interface Fields {
        String COLD_WATER_BILL = "Холодная вода";
        String CUSTOMER_ID = "ID заказчика";
        String DATE = "Дата";
        String GAZ_BILL = "Газ";
        String HOT_WATER_BILL = "Горячая вода";
        String ID = "ID";
        String LIGHT_BILL = "Свет";
        String TARIFF_ID = "ID тарифа";
    }
}
