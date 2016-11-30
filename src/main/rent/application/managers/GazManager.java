package rent.application.managers;

import javafx.collections.ObservableList;
import org.apache.commons.lang3.time.DateUtils;
import rent.application.DatabaseLoader;
import rent.application.singletons.Context;
import rent.constants.Services;
import rent.interfaces.IEntity;
import rent.interfaces.ITableModel;
import rent.model.entities.Customer;
import rent.model.entities.Expense;
import rent.model.services.impl.ExpenseService;
import rent.ui.entities.UiCustomer;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that is responsible for managing current gaz values for
 * every customer.
 *
 * @author zagart
 */
public class GazManager {
    final static private int GAZ_STEP_MAX = DatabaseLoader.EXPENSE_BOUND / 10;
    final private Lock mLock = new ReentrantLock();
    final private Random mRandom = new Random();
    private Map<Customer, Integer> mGazRepository = new HashMap<>();
    private Date mLastRecordDate = new Date(0);
    private boolean mRunning = false;
    private TableManager mTableManager;

    private int getDay(final Date pDate) {
        return DateUtils.toCalendar(pDate).get(Calendar.DAY_OF_MONTH);
    }

    <M extends ITableModel> int getGazForSelectedCustomer(final TableManager<M> pTableManager) {
        if (mTableManager == null) {
            System.out.println("Trying to initialize gaz manager...");
            init(pTableManager);
        } else if (!mRunning) {
            System.out.println("Running.");
            run();
        } else {
            final UiCustomer model = (UiCustomer) pTableManager.getSelectionModel().getSelectedItem();
            if (model != null) {
                return mGazRepository.get(model.extractEntity());
            }
        }
        return 0;
    }

    private int getMonth(final Date pDate) {
        return DateUtils.toCalendar(pDate).get(Calendar.MONTH);
    }

    private <M extends ITableModel> void init(final TableManager<M> pTableManager) {
        final M model = pTableManager.getSelectionModel().getSelectedItem();
        if (model != null) {
            mTableManager = pTableManager;
            System.out.println("Gaz manager initialized.");
            final IEntity entity = model.extractEntity();
            if (!(entity instanceof Customer)) {
                throw new RuntimeException("Wrong table. Customer required");
            }
            for (final Object item : mTableManager.getItems()) {
                UiCustomer customer = (UiCustomer) item;
                mGazRepository.put(customer.extractEntity(), mRandom.nextInt(DatabaseLoader.EXPENSE_BOUND));
            }
            System.out.println("Gaz repository created.");
        }
    }

    private boolean isFirstDayOfMonth(final Date pDate) {
        return (getDay(pDate) == 1);
    }

    private boolean isRunning() {
        return mRunning;
    }

    private void persistIfRequired(final Map<Customer, Integer> pGazRepository, final Date pDate) {
        if (isFirstDayOfMonth(pDate) && getMonth(mLastRecordDate) != getMonth(pDate)) {
            final ExpenseService expenseService = Context.getService(Expense.class);
            for (final Map.Entry<Customer, Integer> pair : pGazRepository.entrySet()) {
                final Customer customer = pair.getKey();
                final long gazExpense = pair.getValue().longValue();
                expenseService.save(new Expense()
                        .setCustomer(customer)
                        .setGazExpense(gazExpense)
                        .setColdWaterExpense(gazExpense - mRandom.nextInt(GAZ_STEP_MAX))
                        .setHotWaterExpense(gazExpense - mRandom.nextInt(GAZ_STEP_MAX))
                        .setLightExpense(gazExpense - mRandom.nextInt(GAZ_STEP_MAX))
                        .setExpenseDate(pDate));
            }
            mLastRecordDate = pDate;
            System.out.println("First day of month. New expenses recorded.");
        }
    }

    private void run() {
        mRunning = true;
        mLock.lock();
        final ThreadManager threadManager = (ThreadManager) Context.getSystemService(Services.THREAD_MANAGER);
        threadManager.execute(() -> {
            synchronized (mLock) {
                while (isRunning()) {
                    try {
                        final ObservableList items = mTableManager.getItems();
                        for (final Object item : items) {
                            final UiCustomer model = (UiCustomer) item;
                            final Customer customer = model.extractEntity();
                            final Integer gaz = mGazRepository.get(customer);
                            mGazRepository.put(customer, gaz + mRandom.nextInt(GAZ_STEP_MAX));
                        }
                        persistIfRequired(mGazRepository, new Date());
                        mLock.wait(1000);
                    } catch (InterruptedException pEx) {
                        System.out.println(pEx.getMessage());
                    }
                }
            }
        });
        mLock.unlock();
    }

    void stop() {
        mRunning = false;
        mTableManager = null;
        System.out.println("Stopped.");
    }
}
