package parsers;

import com.features.Handle;
import com.features.Material;
import com.features.Value;
import com.features.Visual;
import com.main.Knives;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * This class is an implementation of the SAX Parser
 * provides methods to parse a xml-file
 *
 * @author Vladyslav Voitsekh
 * @since 6.12.2015
 */
public class MySAXParser extends DefaultHandler {
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
     * Default constructor
     */
    public MySAXParser() {}

    @Override
    public void startDocument() {
        System.out.println("Start parsing");
    }

    @Override
    public void endDocument() {
        System.out.println("End parsing");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "main:knives":
                current = 1;
                break;
            case "main:knife":
                knife = new Knives.Knife();
                knife.setId(attributes.getValue(0));
                current = 2;
                break;
            case "main:type":
                current = 3;
                break;
            case "main:handy":
                current = 4;
                break;
            case "main:origin":
                current = 5;
                break;
            case "main:visual":
                knife.setVisual(new Visual());
                current = 6;
                break;
            case "features:length":
                current = 7;
                break;
            case "features:width":
                current = 8;
                break;
            case "features:material":
                current = 9;
                break;
            case "features:handle":
                current = 10;
                break;
            case "features:wood":
                current = 11;
                break;
            case "features:drainage":
                current = 12;
                break;
            case "features:value":
                current = 13;
                break;
        }
    }

    @Override
    public void characters(char[] buf, int start, int length) {
        String text = new String(buf, start, length);
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
    }

    @Override
    public void endElement(String uri,String localName,String qName){
        switch(qName){
            case "main:knife":{
                knives.getKnives().add(knife);
                current = 0;
            }
        }
    }

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        MySAXParser par = new MySAXParser();
        parser.parse("knives.xml", par);
        Knives list = par.getKnives();

        for(Knives.Knife temp:list.getKnives()){
            System.out.println(temp.getType());
        }
    }
}
