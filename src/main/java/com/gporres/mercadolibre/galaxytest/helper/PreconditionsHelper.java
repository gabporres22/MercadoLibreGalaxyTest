package com.gporres.mercadolibre.galaxytest.helper;

import com.google.common.base.Preconditions;

public class PreconditionsHelper {
    public static void checkNotNull(final Object object) {
        Preconditions.checkNotNull(object, "[" + Object.class.getName() + "] must be not null");
    }

    public static void checkArgument(final Boolean condition, final String message) {
        Preconditions.checkArgument(condition, "[" + Object.class.getName() + "] " + message);
    }

    public static void checkArgument(final Boolean condition) {
        Preconditions.checkArgument(condition, "[" + Object.class.getName() + "] must be greater than 0.");
    }

    public static void checkNotNullAndArgument(final Object object, final Boolean condition) {
        checkNotNull(object);
        checkArgument(condition);
    }
}
