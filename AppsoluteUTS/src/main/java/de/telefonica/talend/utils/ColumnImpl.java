package de.telefonica.talend.utils;

import de.telefonica.talend.server.Column;
import de.telefonica.talend.server.ColumnName;
import de.telefonica.talend.server.Response;
import de.telefonica.talend.server.ResultSet;

import java.text.SimpleDateFormat;
import java.util.*;

public class ColumnImpl extends Column {

    public Column getColumns(HashMap<String, Object> valueMap) {

        Column column = new Column();
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            ColumnName columnName = new ColumnName();
            columnName.setName(entry.getKey());
            if (entry.getValue() instanceof String)
                columnName.setValue((String) entry.getValue());
            else if (entry.getValue() instanceof Date) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                columnName.setValue(format.format((Date) entry.getValue()));
            }
            column.getColumns().add(columnName);
        }
        return column;
    }
}
