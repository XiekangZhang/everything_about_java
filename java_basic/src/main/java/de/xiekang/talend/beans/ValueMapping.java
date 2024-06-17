package de.xiekang.talend.beans;

public class ValueMapping {
    private Object value;
    private Object key;

    public ValueMapping() {
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }
}
