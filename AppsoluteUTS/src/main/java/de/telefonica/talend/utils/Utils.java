package de.telefonica.talend.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {

    public static String parseXML(Document document, StringBuilder xmlString, String breakPoint) {
        NodeList nodeList = document.getElementsByTagName("*");
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getNodeName() + ": " + nodeList.item(i).getFirstChild().getNodeValue());
        }
        return null;
    }

    private static void parseImpl(NodeList nodeList, LinkedHashMap<String, Object> hashMap) {

    }

    public static String findAttrInNode(NodeList nodeList, StringBuilder jsonString) {
        jsonString.append("{ \n");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getNodeName());
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

}
