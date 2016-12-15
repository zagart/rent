package rent.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * Utitility class with methods for using reflection.
 *
 * @author zagart
 */
public class ReflectionUtil {
    public static Object getGenericObject(Object pTarget, int pParameterPosition) {
        final ParameterizedType parameterizedType = (ParameterizedType) pTarget.getClass().getGenericSuperclass();
        final Class<?> clazz = (Class<?>) parameterizedType.getActualTypeArguments()[pParameterPosition];
        final Constructor<?> constructor = clazz.getConstructors()[0];
        return createGenericObject(constructor);
    }

    private static Object createGenericObject(final Constructor<?> pConstructor) {
        final Object object;
        try {
            object = pConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException pEx) {
            throw new RuntimeException("Failed to get object from generic parameter");
        }
        return object;
    }
}
