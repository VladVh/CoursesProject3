package parsers;

import com.features.Handle;
import com.features.Material;
import com.features.Value;
import com.features.Visual;
import com.main.Knives;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * This class is an implementation of the StAX Parser
 * provides methods to parse a xml-file
 *
 * @author Vladyslav Voitsekh
 * @since 6.12.2015
 */
public class MyStAXParser {
    public Knives getKnives() {
        return knives;
    }

    public void setKnives(Knives knives) {
        this.knives = knives;
    }

    /**
     * The root element in the xml-file
     */
    private Knives knives = new Knives();

    /**
     * Knives child element
     */
    private Knives.Knife knife;

    /**
     * knife counter
     */
    private int current = 0;

    /**
     * Method to parse a file
     * @param fileName path to the .xml
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    public void parsing(String fileName) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName));

        while(eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    parseTagName(startElement, qName);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = event.asCharacters().getData();
                    switch (current) {
                        case 3: knife.setType(text);
                            break;
                        case 4: knife.setHandy(Integer.valueOf(text));
                            break;
                        case 5: knife.setOrigin(text);
                            break;
                        case 7: knife.getVisual().setLength(Integer.valueOf(text));
                            break;
                        case 8: knife.getVisual().setWidth(Integer.valueOf(text));
                            break;
                        case 9: knife.getVisual().setMaterial(Material.fromValue(text));
                            break;
                        case 10: knife.getVisual().setHandle(Handle.fromValue(text));
                            break;
                        case 11: knife.getVisual().setWood(text);
                            break;
                        case 12: knife.getVisual().setDrainage(Value.fromValue(text));
                            break;
                        case 13: knife.getVisual().setValue(Value.fromValue(text));
                            break;
                    }
                    current = 0;
                    break;
                case  XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().equalsIgnoreCase("knife")){
                        knives.getKnives().add(knife);
                    }
                    break;
            }
        }
    }

    /**
     * Method to parse tags
     * @param startElement
     * @param qName
     */
    private void parseTagName(StartElement startElement, String qName) {
        if(qName.equalsIgnoreCase("knives")) {
            System.out.println("Start parsing");
            current = 1;
        } else if(qName.equalsIgnoreCase("knife")) {
            knife = new Knives.Knife();
            Attribute attribute = startElement.getAttributeByName(new QName("id"));
            knife.setId(attribute.getValue());
            current = 2;
        } else if(qName.equalsIgnoreCase("type")) {
            current = 3;
        } else if(qName.equalsIgnoreCase("handy")) {
            current = 4;
        } else if(qName.equalsIgnoreCase("origin")) {
            current = 5;
        } else if(qName.equalsIgnoreCase("visual")) {
            knife.setVisual(new Visual());
            current = 6;
        } else if(qName.equalsIgnoreCase("length")) {
            current = 7;
        } else if(qName.equalsIgnoreCase("width")) {
            current = 8;
        } else if(qName.equalsIgnoreCase("material")) {
            current = 9;
        } else if(qName.equalsIgnoreCase("handle")) {
            current = 10;
        } else if(qName.equalsIgnoreCase("wood")) {
            current = 11;
        } else if(qName.equalsIgnoreCase("drainage")) {
            current = 12;
        } else if(qName.equalsIgnoreCase("value")) {
            current = 13;
        }
    }

    public static void main(String[] args) {
        MyStAXParser stAXParser = new MyStAXParser();
        try {
            stAXParser.parsing("knives.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        Knives list = stAXParser.getKnives();

        for(Knives.Knife temp:list.getKnives()){
            System.out.println(temp.getVisual().getHandle());
        }
    }
}
