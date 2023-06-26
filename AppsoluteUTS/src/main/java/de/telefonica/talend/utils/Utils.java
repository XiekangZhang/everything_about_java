package de.telefonica.talend.utils;

import com.bmc.ao.xsd._2008._09.soa.ExecuteProcessResponse;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    // todo: think of error handling
    public static String parseXML(Document document, String pattern) {
        String extractedXML = extractXML(document, pattern);
        StringWriter stringWriter = new StringWriter();

        try {
            Document newDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new ByteArrayInputStream(extractedXML.getBytes()));
            Element element = newDocument.getDocumentElement();
            Attr attr = newDocument.createAttribute("xmlns");
            attr.setValue("http://server.talend.telefonica.de/");
            element.setAttributeNode(attr);

            Transformer xform = TransformerFactory.newInstance().newTransformer();
            xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            xform.transform(new DOMSource(newDocument.getFirstChild()), new StreamResult(stringWriter));
        } catch (Exception e) {
            throw new RuntimeException("Can not adding namespace");
        }
        return stringWriter.toString();
    }

    private static String extractXML(Document document, String pattern) {
        NodeList result = document.getElementsByTagName(pattern);
        StringWriter stringWriter = new StringWriter();
        try {
            Transformer xform = TransformerFactory.newInstance().newTransformer();
            xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            xform.transform(new DOMSource(result.item(0)), new StreamResult(stringWriter));
        } catch (Exception e) {
            throw new RuntimeException("Can not extract xml file");
        }
        return stringWriter.toString();
    }

    public static void updateResponse(String exePath, String rawResponsePath) {
        // todo: think of a good way --> xslt validation
        try {
            Path filePath = Path.of(exePath);
            Path filePath2 = Path.of(rawResponsePath);
            String content = Files.readString(filePath).replace("${placeholder}", Files.readString(filePath2));
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("C:\\dev\\workspace\\AppsoluteUTS\\src\\test\\resources\\full_response.xml"));
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
