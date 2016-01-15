package transformation;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Vlad on 10.12.2015.
 */
public class HtmlTransform {
    private String xmlPath;
    private String xslPath;

    public HtmlTransform(String xmlPath, String xslPath) {
        this.xmlPath = xmlPath;
        this.xslPath = xslPath;
    }

    public void transform(String filename) {
        try {
            TransformerFactory tFact = TransformerFactory.newInstance();
            Source xmlDoc = new StreamSource(xmlPath);
            Source xslDoc = new StreamSource(xslPath);
            OutputStream htmlFile = new FileOutputStream(filename);
            Transformer transformer = tFact.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new HtmlTransform("Knives.xml", "knives.xsl").transform("knives.html");
    }
}
