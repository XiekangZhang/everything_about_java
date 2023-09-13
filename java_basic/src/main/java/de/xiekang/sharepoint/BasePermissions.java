package de.xiekang.sharepoint;

import java.util.ArrayList;
import java.util.List;

public class BasePermissions {
    private final long low;
    private final long high;

    public BasePermissions(long low, long high) {
        this.low = low;
        this.high = high;
    }

    public String[] parse() {
        List<String> result = new ArrayList<>();
        for (PermissionKind value : PermissionKind.values()) {
            if(has(value)){
                result.add(value.toString());
            }
        }
        return result.toArray(new String[0]);
    }

    public Boolean has(PermissionKind perm)
    {
        switch (perm)
        {
            case EmptyMask:
                return true;
            case FullMask:
                if ((this.high & 32767) == 32767)
                    return this.low == 65535;
                return false;
            default:
                long n = perm.getValue() - 1;
                long val = 1;
                if (n >= 0 && n < 32)
                    return 0 != (this.low & (val << n));
                if (n >= 32 && n < 64)
                    return 0 != (this.high & (val << n - 32));
                return false;
        }
    }
}
