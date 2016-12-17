package rent.model.entities;

import javafx.scene.control.TableView;
import rent.application.utils.JavaFxUtil;
import rent.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity for Bill model.
 *
 * @author zagart
 */
@Entity
@Table(name = "bill", catalog = "rent")
public class Bill implements IEntity {
    private Date mBillDate;
    private Long mColdWaterBill;
    private Customer mCustomer;
    private Long mGazBill;
    private Long mHotWaterBill;
    private Long mId;
    private Long mLightBill;
    private Tariff mTariff;

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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return mId;
    }

    @Override
    public TableView getTableView() {
        return JavaFxUtil.createTable(
                Fields.ID,
                Fields.CUSTOMER_ID,
                Fields.TARIFF_ID,
                Fields.LIGHT_BILL,
                Fields.GAZ_BILL,
                Fields.COLD_WATER_BILL,
                Fields.HOT_WATER_BILL,
                Fields.DATE);
    }

    public Bill setId(Long pId) {
        mId = pId;
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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Tariff.class)
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
