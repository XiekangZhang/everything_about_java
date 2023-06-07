/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.telefonica.service.talendbus.test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telefonica.service.talendbus.Event;
import de.telefonica.service.talendbus.TransformHelper;
import jakarta.xml.bind.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * @author cbach
 */
public class TestValidation {

    public static void test1() throws JAXBException, IOException {
        JAXBContext c = JAXBContext.newInstance(Event.class);
        Unmarshaller unmarshaller = c.createUnmarshaller();
        JAXBElement<Event> rootEvent = (JAXBElement<Event>) unmarshaller.unmarshal(new StringReader(
                "<tal:Event xmlns:tal=\"http://www.telefonica.de/service/talendbus\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" destination=\"?\" source=\"?\">\n" +
                        "<tal:type>apt_event</tal:type>\n" +
                        "<tal:version>0.3</tal:version>\n" +
                        "<!--Optional:-->\n" +
                        "<tal:id>EV0000000031APT</tal:id>\n" +
                        "<apt:aptUpdateEvent xmlns:apt=\"http://telefonica.com/wos/ifaces\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tal=\"http://www.telefonica.de/service/talendbus\">\n" +
                        "<apt:id>EV0000000031APT</apt:id>\n" +
                        "<apt:type>TEST CB</apt:type>\n" +
                        "<apt:version>1.0</apt:version>\n" +
                        "<apt:linkStatusUpdate>\n" +
                        "<apt:userLinkId>xxx</apt:userLinkId>\n" +
                        "<apt:revisionNumber>1</apt:revisionNumber>\n" +
                        "<apt:linkType>1</apt:linkType>\n" +
                        "<apt:aufbauart>xxx</apt:aufbauart>\n" +
                        "<apt:statusUpdate>\n" +
                        "<apt:name>status name</apt:name>\n" +
                        "<apt:type>status type</apt:type>\n" +
                        "<apt:value>2023-01-02</apt:value>\n" +
                        "</apt:statusUpdate>\n" +
                        "</apt:linkStatusUpdate>\n" +
                        "</apt:aptUpdateEvent>\n" +
                        "</tal:Event>\n"));
        Event event = rootEvent.getValue();

        final TransformHelper.TransformationResult tResult = TransformHelper.transform(event.getAny(), new File(
                "C:\\opt\\app\\susy\\etl\\tst\\activiti\\r_apt_talendbus_new\\data\\input\\apt_event[0.3].xsl"
        ));
        System.err.println(event.getAny());
        System.err.println(tResult.content);
        //java.nio.file.Files.createFile(java.nio.file.Paths.get("test.json"));
        java.nio.file.Files.write(java.nio.file.Paths.get("test.json"), tResult.content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void test2() throws JAXBException, TransformerException, IOException {
        JAXBContext c = JAXBContext.newInstance(Event.class);
        Unmarshaller unmarshaller = c.createUnmarshaller();
        JAXBElement<Event> rootEvent = (JAXBElement<Event>) unmarshaller.unmarshal(new StringReader(
                "<tal:Event xmlns:tal=\"http://www.telefonica.de/service/talendbus\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" destination=\"?\" source=\"?\">\n" +
                        "<tal:type>apt_event</tal:type>\n" +
                        "<tal:version>0.3</tal:version>\n" +
                        "<!--Optional:-->\n" +
                        "<tal:id>EV0000000031APT</tal:id>\n" +
                        "<apt:aptUpdateEvent xmlns:apt=\"http://telefonica.com/wos/ifaces\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tal=\"http://www.telefonica.de/service/talendbus\">\n" +
                        "<apt:id>EV0000000031APT</apt:id>\n" +
                        "<apt:type>TEST CB</apt:type>\n" +
                        "<apt:version>1.0</apt:version>\n" +
                        "<apt:linkStatusUpdate>\n" +
                        "<apt:userLinkId>xxx</apt:userLinkId>\n" +
                        "<apt:revisionNumber>1</apt:revisionNumber>\n" +
                        "<apt:linkType>1</apt:linkType>\n" +
                        "<apt:aufbauart>xxx</apt:aufbauart>\n" +
                        "<apt:statusUpdate>\n" +
                        "<apt:name>status name</apt:name>\n" +
                        "<apt:type>status type</apt:type>\n" +
                        "<apt:value>2023-01-02</apt:value>\n" +
                        "</apt:statusUpdate>\n" +
                        "</apt:linkStatusUpdate>\n" +
                        "</apt:aptUpdateEvent>\n" +
                        "</tal:Event>\n"));
        Event event = rootEvent.getValue();
        NodeList nodeList = event.getAny().getElementsByTagName("*");
        Map<String, HashMap<String, String>> jsonStructure = new HashMap<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (jsonStructure.containsKey(node.getParentNode().getNodeName())) {
                (jsonStructure.get(node.getParentNode().getNodeName())).put(node.getNodeName(), node.getFirstChild().getNodeValue());
            } else {
                HashMap<String, String> jsonSubstructure = new HashMap<>();
                jsonSubstructure.put(node.getNodeName().toString(), node.getFirstChild().getNodeValue());
                jsonStructure.put(node.getParentNode().getNodeName(), jsonSubstructure);
            }
        }

        HashMap<String, Object> jsonString = new HashMap<>();
        for (String key: jsonStructure.get("apt:aptUpdateEvent").keySet()) {
            jsonString.put(key, jsonStructure.get("apt:aptUpdateEvent").get(key));
        }
        jsonString.put("apt:linkStatusUpdate", jsonStructure.get("apt:linkStatusUpdate"));
        jsonString.put("apt:statusUpdate", jsonStructure.get("apt:statusUpdate"));
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonString);
        System.out.println(json.replace("apt:", ""));
    }

    public static void test3() throws JAXBException, JsonProcessingException {
        JAXBContext c = JAXBContext.newInstance(Event.class);
        Unmarshaller unmarshaller = c.createUnmarshaller();
        JAXBElement<Event> rootEvent = (JAXBElement<Event>) unmarshaller.unmarshal(new StringReader(
                "<tal:Event xmlns:tal=\"http://www.telefonica.de/service/talendbus\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" destination=\"?\" source=\"?\">\n" +
                        "<tal:type>apt_event</tal:type>\n" +
                        "<tal:version>0.3</tal:version>\n" +
                        "<!--Optional:-->\n" +
                        "<tal:id>EV0000000031APT</tal:id>\n" +
                        "<apt:aptUpdateEvent xmlns:apt=\"http://telefonica.com/wos/ifaces\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tal=\"http://www.telefonica.de/service/talendbus\">\n" +
                        "<apt:id>EV0000000031APT</apt:id>\n" +
                        "<apt:type>TEST CB</apt:type>\n" +
                        "<apt:version>1.0</apt:version>\n" +
                        "<apt:linkStatusUpdate>\n" +
                        "<apt:userLinkId>xxx</apt:userLinkId>\n" +
                        "<apt:revisionNumber>1</apt:revisionNumber>\n" +
                        "<apt:linkType>1</apt:linkType>\n" +
                        "<apt:aufbauart>xxx</apt:aufbauart>\n" +
                        "<apt:statusUpdate>\n" +
                        "<apt:name>status name</apt:name>\n" +
                        "<apt:type>status type</apt:type>\n" +
                        "<apt:value>2023-01-02</apt:value>\n" +
                        "</apt:statusUpdate>\n" +
                        "</apt:linkStatusUpdate>\n" +
                        "</apt:aptUpdateEvent>\n" +
                        "</tal:Event>\n"));
        Event event = rootEvent.getValue();
        NodeList nodeList = event.getAny().getChildNodes();
        StringBuilder jsonStructure = new StringBuilder();
        String json = findAttrInNode(nodeList, jsonStructure);
        System.out.println(json.replace("apt:", ""));

        ObjectMapper mapper = new ObjectMapper();
        String test = mapper.readTree(json.replace("apt:", "")).toPrettyString();
        System.out.println(test.getBytes());
        /**
        ObjectMapper mapper = new ObjectMapper();
        Object jsonObject = mapper.readValue(json.replace("apt:", ""), Event.class);
        String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        System.out.println(indented);**/
    }

    public static String findAttrInNode(NodeList nodeList, StringBuilder jsonString) {
        jsonString.append("{ \n");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NodeList childrenNodelist = node.getChildNodes();
            if (childrenNodelist.getLength() - 1 == 0) {
                jsonString.append(String.format("\t\"%s\": \"%s\", \n", node.getNodeName(), node.getFirstChild().getNodeValue()));
            } else if (childrenNodelist.getLength() - 1 > 0) {
                jsonString.append(String.format("\t\"%s\": \n", node.getNodeName()));
                findAttrInNode(childrenNodelist, jsonString);
                if (jsonString != null) {
                    jsonString.append("\n}");
                    return jsonString.toString();
                }
            }
        }
        jsonString.replace(jsonString.toString().length() - 3, jsonString.toString().length() - 2, "");
        jsonString.append("\n}");
        return null;
    }

    public static void main(String[] args) throws JAXBException, IOException, TransformerException {
        TestValidation.test3();
    }

}
