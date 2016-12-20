package rent.application.utils;

import rent.constants.ExceptionConstants;
import rent.application.managers.TableManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * Utility class with methods for using reflection.
 *
 * @author zagart
 */
public class ReflectionUtil {
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getGenericClass(final Object pTarget, final int pParameterPosition) {
        if (pTarget instanceof TableManager) {
            System.out.println("Success");
        }
        final ParameterizedType parameterizedType = (ParameterizedType) pTarget.getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[pParameterPosition];
    }

    public static Object getGenericObject(final Object pTarget, final int pParameterPosition) {
        final Class<?> clazz = getGenericClass(pTarget, pParameterPosition);
        return createGenericObject(clazz);
    }

    public static Object createGenericObject(final Class<?> pClass) {
        final Constructor<?> constructor = pClass.getConstructors()[0];
        final Object object;
        try {
            object = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException pEx) {
            throw new RuntimeException(ExceptionConstants.GENERIC_PARAMETER_EXTRACTION_EXCEPTION);
        }
        return object;
    }
}
