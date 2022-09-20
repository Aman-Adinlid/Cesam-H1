package aman.se;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlToObject {

    public static void main(String[] args) {

        final String inputMessageAddMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                "                          <cal:addMessage xmlns:cal=\"http://klockren.se/version/1.0/schemas/integration/callback\">\r\n" +
                "							<cal:flowInstanceID>1337</cal:flowInstanceID>\r\n" +
                "                              <cal:externalID>\r\n" +
                "                                <cal:ID>T800</cal:ID>\r\n" +
                "                                <cal:System>Skynet</cal:System>\r\n" +
                "                              </cal:externalID>\r\n" +
                "                              <cal:message>\r\n" +
                "                                <cal:added>2022-09-09T11:57:11.9283614+02:00</cal:added>\r\n" +
                "                                <cal:attachments>\r\n" +
                "                                    <cal:encodedData>a2FuZWxidWxsZQ==</cal:encodedData>\r\n" +
                "                                    <cal:filename>bulle.txt</cal:filename>\r\n" +
                "                                    <cal:size>11</cal:size>\r\n" +
                "                                </cal:attachments>\r\n" +
                "                                <cal:attachments>\r\n" +
                "                                    <cal:encodedData>YmFuYW4=</cal:encodedData>\r\n" +
                "                                    <cal:filename>frukt.txt</cal:filename>\r\n" +
                "                                    <cal:size>12</cal:size>\r\n" +
                "                                </cal:attachments>\r\n" +
                "                                <cal:message>Detta är ett testmeddelande.</cal:message>\r\n" +
                "                                <cal:userID>TestUser001</cal:userID>\r\n" +
                "                                <cal:readReceiptEnabled>false</cal:readReceiptEnabled>\r\n" +
                "                              </cal:message>\r\n" +
                "                              <cal:principal>\r\n" +
                "                                <cal:name>Dwight Shrute</cal:name>\r\n" +
                "                                <cal:userID>dwishu</cal:userID>\r\n" +
                "                              </cal:principal>\r\n" +
                "                          </cal:addMessage>";


        String inputMessageSetStatus = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
                "      <cal:setStatus>\r\n" +
                "         <cal:flowInstanceID>700</cal:flowInstanceID>\r\n" +
                "         <cal:externalID>\r\n" +
                "            <cal:ID>9000</cal:ID>\r\n" +
                "            <cal:System>HAL</cal:System>\r\n" +
                "         </cal:externalID>\r\n" +
                "         <cal:statusID>2</cal:statusID>\r\n" +
                "         <cal:statusAlias>Upptagen</cal:statusAlias>\r\n" +
                "         <cal:principal>\r\n" +
                "            <cal:name>Michael Scott</cal:name>\r\n" +
                "            <cal:userID>micsco</cal:userID>\r\n" +
                "         </cal:principal>\r\n" +
                "      </cal:setStatus>";


        Document doc = convertStringToDocument(inputMessageAddMessage);
        Document doc2 = convertStringToDocument(inputMessageSetStatus);
        String str = convertDocumentToString(doc);
        String str2 = convertDocumentToString(doc2);
        System.out.println(str);
        System.out.println(str2);


    }

    private static String convertDocumentToString(Document doc) {
        // use transform
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String put = writer.getBuffer().toString();
            return put;
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Document convertStringToDocument(String inputMessageAddMessage) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();
            //get doc
            Document doc = builder.parse(new InputSource(new StringReader(inputMessageAddMessage)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
