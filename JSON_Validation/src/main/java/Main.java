import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema jsonSchema = factory.getSchema(Main.class.getResourceAsStream("/test1.json"));

        File file = new File(Objects.requireNonNull(Main.class.getResource("/testcases/test")).getFile());

        for (File f : Objects.requireNonNull(file.listFiles())) {
            JsonNode jsonNode = mapper.readTree(new FileInputStream(f));
            Set<ValidationMessage> validationResult = jsonSchema.validate(jsonNode);
            System.out.println("##########################################################################");
            if (validationResult.isEmpty()) {
                System.out.println(f.getName() + " is valid");
            } else {
                System.out.println(f.getName() + " is invalid");
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
            }
        }

        String test = "{\n" +
                "  \"xpds_responseHeader\": {\n" +
                "    \"sessionID\": 7152708,\n" +
                "    \"sessionKey\": \"96ade233a57044c4dae835e999fcb1872e0f4f5638ded4665fb7af1a651d6c23\",\n" +
                "    \"version\": 0,\n" +
                "    \"timestamp\": \"2024-05-06T13:26:46.57+01:00\",\n" +
                "    \"errorType\": \"\",\n" +
                "    \"errorMessage\": \"\",\n" +
                "    \"errorMessageId\": \"\",\n" +
                "    \"ignoreWarning\": \"\",\n" +
                "    \"listHeader\": {\n" +
                "      \"lastRow\": \"0\",\n" +
                "      \"offset\": 0\n" +
                "    },\n" +
                "    \"meta\": \"\"\n" +
                "  },\n" +
                "  \"xpds_responseBody\": []\n" +
                "}";
    }
}
