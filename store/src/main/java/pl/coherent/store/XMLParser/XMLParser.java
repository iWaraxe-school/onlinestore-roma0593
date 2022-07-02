package pl.coherent.store.XMLParser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {
    public static final String XMLPATH = "store/src/main/resources/config.xml";

    public Map<String, String> getFieldSortOrderMap() {
        Map<String, String> fieldSortDirectionMap = new LinkedHashMap<>();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(XMLPATH));
            doc.getDocumentElement().normalize();
            Node first = doc.getElementsByTagName("sort").item(0);
            NodeList nodeList = first.getChildNodes();
            Node current;

            for (int i = 0; i < nodeList.getLength(); i++) {
                current = nodeList.item(i);
                fieldSortDirectionMap.put(current.getNodeName(), current.getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fieldSortDirectionMap;
    }
}
