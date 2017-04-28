package trycatch;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by Zoli on 13/04/2017.
 */
public class Test {

    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public static void main(String ... args  ){

        System.out.println("hello");

        String errorString = "errorstring";
        DocumentBuilder db = null;
        Document document = null;
        try {
            db = documentBuilderFactory.newDocumentBuilder();
            document = db.newDocument();
            Element rootElement = document.createElement("SyncNBNMRWOOUT-RES");
            document.appendChild(rootElement);
            rootElement.setAttribute("xmlns", "http://www.ibm.com/maximo");
            rootElement.setAttribute("baseLanguage", "EN");
            rootElement.setAttribute("transLanguage", "EN");

            rootElement.setAttribute("event", "1");

            Element NBNMRWOOUTRESSet = document.createElement("NBNMRWOOUT-RESSet");
            rootElement.appendChild(NBNMRWOOUTRESSet);

            Element NBNMIFINTERACTIONLOG = document.createElement("NBNMIFINTERACTIONLOG");
            NBNMRWOOUTRESSet.appendChild(NBNMIFINTERACTIONLOG);

            Element NBNCORRELATIONID = document.createElement("NBNCORRELATIONID");
            NBNCORRELATIONID.setTextContent(null);
            NBNMIFINTERACTIONLOG.appendChild(NBNCORRELATIONID);

            Element ERRORCODE = document.createElement("ERRORCODE");
            ERRORCODE.setTextContent("12");
            NBNMIFINTERACTIONLOG.appendChild(ERRORCODE);

            Element ERRORMSG = document.createElement("ERRORMSG");
            ERRORMSG.setTextContent("error");
            NBNMIFINTERACTIONLOG.appendChild(ERRORMSG);



            Element INTERACTIONSTATUS = document.createElement("INTERACTIONSTATUS");
            INTERACTIONSTATUS.setTextContent("ERROR");
            NBNMIFINTERACTIONLOG.appendChild(INTERACTIONSTATUS);

            Element RECORDKEY = document.createElement("RECORDKEY");
            RECORDKEY.setTextContent("12");
            NBNMIFINTERACTIONLOG.appendChild(RECORDKEY);

            String doc = getStringFromDocument(document);
            System.out.println(errorString + " : " +  doc);



        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("after try block");

    }



    private static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
