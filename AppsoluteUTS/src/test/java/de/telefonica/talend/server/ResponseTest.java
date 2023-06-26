package de.telefonica.talend.server;

import de.telefonica.talend.utils.ColumnImpl;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;


class ResponseTest {
    static ResultSet resultSet;
    static Connection con;

    @BeforeAll
    static void DBConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@deapvimz:11011/VDCMZ",
                    "TISADMIN_TEST",
                    "clywf2020@"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void DBClose() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getResponse() {
        Response response = new Response();
        try {
            Statement statement = con.createStatement();
            String query = "SELECT \n" +
                    "\t\tWORK_LOG_ID,\n" +
                    "\t\tINFRASTRUCTURE_CHANGE_ID,\n" +
                    "\t\tDESCRIPTION,\n" +
                    "\t\tDETAILED_DESCRIPTION,\n" +
                    "\t\tCOMMUNICATION_SOURCE,\n" +
                    "\t\tWORK_LOG_TYPE,\n" +
                    "\t\tVIEW_ACCESS,\n" +
                    "\t\tWEATHERMAP_RELEVANT_CHG,\n" +
                    "\t\tWEATHERMAP_LONGTERM_CHG,\n" +
                    "\t\tWORK_LOG_SUBMIT_DATE,\n" +
                    "\t\tLAST_MODIFIED_DATE,\n" +
                    "\t\tWORK_LOG_DATE\n" +
                    "FROM \n" +
                    "\t\tCHG_WORKLOG\n" +
                    "WHERE INFRASTRUCTURE_CHANGE_ID in 'change1'\n" +
                    "ORDER BY WORK_LOG_SUBMIT_DATE DESC";
            resultSet = statement.executeQuery(query);
            // todo: while (resultSet.next()) {}
            // status
            response.setStatus("success");

            // resultset & row
            de.telefonica.talend.server.ResultSet resultSet1 = new de.telefonica.talend.server.ResultSet();

            ColumnImpl columnImpl = new ColumnImpl();
            int rows = 0;
            int colCount = resultSet.getMetaData().getColumnCount();
            Long start = new Date().getTime();

            while (resultSet.next()) {
                rows++;
                HashMap<String, Object> hashMap = new HashMap<>();
                for (int index = 1; index <= colCount; index++) {
                    String colName = resultSet.getMetaData().getColumnName(index);
                    Object value = resultSet.getObject(index);
                    hashMap.put(colName, value);
                }
                Column column = columnImpl.getColumns(hashMap);
                resultSet1.getRows().add(column);
            }
            Long end = new Date().getTime();

            // metadata
            Metadata metadata = new Metadata();
            metadata.setQueryExecuted(query);
            metadata.setExecutionMilliseconds(end-start);
            metadata.setRowCount(rows);
            metadata.setColumnCount(colCount);
            metadata.setStatus("success");

            resultSet1.setMetadata(metadata);
            response.setResultSet(resultSet1);

            JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(response,
                    new File("C:/dev/workspace/AppsoluteUTS/src/test/resources/response.xml"));
        } catch (Exception e) {
            response.setStatus("failed");
            e.printStackTrace();
        }
    }
}