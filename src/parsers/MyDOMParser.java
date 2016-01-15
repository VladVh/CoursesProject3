package parsers;

import com.features.Handle;
import com.features.Material;
import com.features.Value;
import com.features.Visual;
import com.main.Knives;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * This class implements ErrorHandler and is responsible
 * for reporting for mistakes
 */
class MyErrorHandler implements ErrorHandler {
    boolean flag = true;

    public void warning(SAXParseException e) {
        System.err.println("warning: " + getLineAddress(e) +
                " -> " + e.getMessage());
    }

    public void error(SAXParseException e) {
        flag = false;
        System.err.println((getLineAddress(e) +
                " -> " + e.getMessage()));
    }

    public void fatalError(SAXParseException e) {
        flag = false;
        System.err.println(getLineAddress(e) +
                " -> " + e.getMessage());
    }

    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}

/**
 * This class is an implementation of the StAX Parser
 * provides methods to parse a xml-file
 *
 * @author Vladyslav Voitsekh
 * @since 6.12.2015
 */
public class MyDOMParser {
    /**
     * The root element in the xml-file
     */
    private Knives knives = new Knives();

    /**
     * Default constructor
     */
    public MyDOMParser() {}

    /**
     * Method to parse a file
     * @param filename path to the file
     * @return all the elements Knife in the .xml
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Knives parsing(String filename) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document document = documentBuilder.parse(filename);
        DOMBuilder jDomBuilder = new DOMBuilder();
        org.jdom2.Document jdomDocument = jDomBuilder.build(document);

        Element root = jdomDocument.getRootElement();

        List<Element> listKnives = root.getChildren("knife", Namespace.getNamespace("http://main.com/"));
        for (Element knife : listKnives) {
            Knives.Knife newKnife = new Knives.Knife();
            newKnife.setId(knife.getAttributeValue("id"));
            newKnife.setType(knife.getChildText("type", Namespace.getNamespace("http://main.com/")));
            newKnife.setHandy(Integer.valueOf(knife.getChildText("handy", Namespace.getNamespace("http://main.com/"))));
            newKnife.setOrigin(knife.getChildText("origin", Namespace.getNamespace("http://main.com/")));

            Element visual = knife.getChild("visual", Namespace.getNamespace("http://main.com/"));
            newKnife.setVisual(new Visual());
            newKnife.getVisual().setLength(Integer.valueOf(visual.getChildText("length",
                    Namespace.getNamespace("http://features.com/"))));
            newKnife.getVisual().setWidth(Integer.valueOf(visual.getChildText("width",
                    Namespace.getNamespace("http://features.com/"))));
            newKnife.getVisual().setMaterial(Material.fromValue(visual.getChildText("material",
                    Namespace.getNamespace("http://features.com/"))));
            newKnife.getVisual().setHandle(Handle.fromValue(visual.getChildText("handle",
                    Namespace.getNamespace("http://features.com/"))));
            newKnife.getVisual().setWood(visual.getChildText("wood",
                    Namespace.getNamespace("http://features.com/")));
            newKnife.getVisual().setDrainage(Value.fromValue(visual.getChildText("drainage",
                    Namespace.getNamespace("http://features.com/"))));
            newKnife.getVisual().setValue(Value.fromValue(visual.getChildText("value",
                    Namespace.getNamespace("http://features.com/"))));
            knives.getKnives().add(newKnife);
        }
        return knives;
    }

    /**
     * Checks whether the xml-file is valid or not
     * @param xmlFile path to .xml
     * @param xsdFile path to .xsd
     * @return 'true' if valid and 'false' if not valid
     * @throws SAXException
     * @throws IOException
     */
    public boolean validation(String xmlFile, String xsdFile) throws SAXException, IOException {
        DOMParser parser = new DOMParser();
        MyErrorHandler handler = new MyErrorHandler();
        try {

            parser.setErrorHandler(handler);
            parser.setFeature(
                    "http://xml.org/sax/features/validation", true);
            parser.setFeature(
                    "http://apache.org/xml/features/validation/schema", true);
            parser.setFeature(
                    "http://apache.org/xml/features/validation/schema-full-checking",
                    true);
            parser.setProperty(
                    "http://apache.org/xml/properties/schema/external-schemaLocation", xsdFile);

            parser.parse(xmlFile);

        } catch (SAXNotRecognizedException e) {
            e.printStackTrace();
        }
        return handler.flag;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        MyDOMParser par=new MyDOMParser();
        Knives knives1 = par.parsing("knives.xml");

        for(Knives.Knife knife: knives1.getKnives()){
            System.out.println(knife.getOrigin());
        }
        System.out.println(par.validation("knives.xml", "knives.xsd"));

    }

}
