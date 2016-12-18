package rent.application.singletons;

import rent.interfaces.IEntity;
import rent.interfaces.IService;
import rent.model.entities.*;
import rent.model.services.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contains objects of services of application.
 *
 * @author zagart
 * @see IService
 */
public class Context {
    private Map<Class<?>, IService> mDaoRepository = new HashMap<Class<?>, IService>() {
        {
            put(Bill.class, new BillService());
            put(Customer.class, new CustomerService());
            put(Expense.class, new ExpenseService());
            put(Passport.class, new PassportService());
            put(Payment.class, new PaymentService());
            put(Tariff.class, new TariffService());
        }
    };

    private Context() {
    }

    /**
     * Method returns service for entity, whose class passed through parameter.
     *
     * @param pEntityClass Class of entity that requested service
     * @return {@link IService} implementation object
     */
    @SuppressWarnings("unchecked")
    public static <S extends IService, E extends IEntity> S getService(final Class<E> pEntityClass) {
        return (S) SingletonHolder.CONTEXT.mDaoRepository.get(pEntityClass);
    }

    private static class SingletonHolder {
        private static final Context CONTEXT = new Context();
    }
}
