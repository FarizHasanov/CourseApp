package az.com.company.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumAvailableStatus {

    ACTIVE(1), DEACTIVE(0);

    private int value;

    private static final Map<Integer, EnumAvailableStatus> VALUES = new HashMap<Integer, EnumAvailableStatus>();

    static {
        for (EnumAvailableStatus type : EnumAvailableStatus.values()) {
            VALUES.put(type.value, type);
        }
    }

    private EnumAvailableStatus(int enumValue) {
        this.value = enumValue;
    }

    public Integer getValue() {
        return value;
    }

    public static EnumAvailableStatus getEnum(Integer value) {
        if (value == null)
            return null;

        return VALUES.get(value.intValue());
    }
}
