package com.gporres.mercadolibre.galaxytest.helper;

import com.google.common.base.Preconditions;

public class PreconditionsHelper {
    public static String GREATER_THAN_FIRST_PARAM = "must be greather than first parameter";
    public static String GREATER_THAN_ZERO = "must be greater than or equal to zero.";
    public static String DIFFERENT_FROM_ZERO = "must be different from zero.";

    public static void checkNotNull(final Object object, final String name) {
        Preconditions.checkNotNull(object, "[" + name + "] must be not null.");
    }

    public static void checkArgument(final Boolean condition, final String name, final String message) {
        Preconditions.checkArgument(condition, "[" + name + "] " + message);
    }

    public static void checkNotNullAndArgument(final Object object, final String name, final Boolean condition, final String message) {
        checkNotNull(object, name);
        checkArgument(condition, name, message);
    }
}
