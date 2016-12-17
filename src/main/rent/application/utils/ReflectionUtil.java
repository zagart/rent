package rent.application.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * Utility class with methods for using reflection.
 *
 * @author zagart
 */
public class ReflectionUtil {
    public static Object getGenericObject(Object pTarget, int pParameterPosition) {
        final ParameterizedType parameterizedType = (ParameterizedType) pTarget.getClass().getGenericSuperclass();
        final Class<?> clazz = (Class<?>) parameterizedType.getActualTypeArguments()[pParameterPosition];
        return createGenericObject(clazz);
    }

    public static Object createGenericObject(final Class<?> pClass) {
        final Constructor<?> constructor = pClass.getConstructors()[0];
        final Object object;
        try {
            object = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException pEx) {
            throw new RuntimeException("Failed to get object from generic parameter");
        }
        return object;
    }
}
