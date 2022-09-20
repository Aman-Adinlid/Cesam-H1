package aman.se;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ExternalMessageTest {

    ExternalMessage externalMessage = new ExternalMessage();
    String inputMessageAddMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
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


    private Map<String, String> createExpectedAttributesMapAddMessage() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("addMessage_flowInstanceID", "1337");
        expectedMap.put("addMessage_externalID_ID", "T800");
        expectedMap.put("addMessage_externalID_System", "Skynet");
        expectedMap.put("addMessage_message_message_added", "2022-09-09T11:57:11.9283614+02:00");
        expectedMap.put("addMessage_message_attachments_encodedData", "a2FuZWxidWxsZQ==");
        expectedMap.put("addMessage_message_attachments_filename", "bulle.txt");
        expectedMap.put("addMessage_message_attachments_size", "11");
        expectedMap.put("addMessage_message_attachments_encodedData_2", "YmFuYW4=");
        expectedMap.put("addMessage_message_attachments_filename_2", "frukt.txt");
        expectedMap.put("addMessage_message_attachments_size_2", "12");
        expectedMap.put("addMessage_message_message", "Detta är ett testmeddelande.");
        expectedMap.put("addMessage_message_userID", "TestUser001");
        expectedMap.put("addMessage_message_readReceiptEnabled", "false");
        expectedMap.put("addMessage_principal_name", "Dwight Shrute");
        expectedMap.put("addMessage_principal_userID", "dwishu");
        return expectedMap;
    }

    private Map<String, String> createExpectedAttributesMapSetStatus() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("setStatus_flowInstanceID", "700");
        expectedMap.put("setStatus_externalID_ID", "9000");
        expectedMap.put("setStatus_externalID_System", "HAL");
        expectedMap.put("setStatus_statusID", "2");
        expectedMap.put("setStatus_statusAlias", "Upptagen");
        expectedMap.put("setStatus_principal_name", "Michael Scott");
        expectedMap.put("setStatus_principal_userID", "micsco");
        return expectedMap;
    }


    @Test
    public void addMessage() {
        Map<String, String> actualMap = externalMessage.createAttributesMap(inputMessageAddMessage);
        Map<String, String> expectedMap = createExpectedAttributesMapAddMessage();
        assertEquals(expectedMap.get("1337"), actualMap.get("addMessage_flowInstanceID"));
        assertEquals(expectedMap.get("T800"), actualMap.get("addMessage_externalID_ID"));
        assertEquals(expectedMap.get("Skynet"), actualMap.get("addMessage_externalID_System"));
        assertEquals(expectedMap.get("2022-09-09T11:57:11.9283614+02:00"), actualMap.get("addMessage_message_message_added"));
        assertEquals(expectedMap.get("a2FuZWxidWxsZQ=="), actualMap.get("addMessage_message_attachments_encodedData"));
        assertEquals(expectedMap.get("bulle.txt"), actualMap.get("addMessage_message_attachments_filename"));
        assertEquals(expectedMap.get("11"), actualMap.get("addMessage_message_attachments_size"));
        assertEquals(expectedMap.get("YmFuYW4="), actualMap.get("addMessage_message_attachments_encodedData_2"));
        assertEquals(expectedMap.get("frukt.txt"), actualMap.get("addMessage_message_attachments_filename_2"));
        assertEquals(expectedMap.get("12"), actualMap.get("addMessage_message_attachments_size_2"));
        assertEquals(expectedMap.get("Detta är ett testmeddelande."), actualMap.get("addMessage_message_message"));
        assertEquals(expectedMap.get("TestUser001"), actualMap.get("addMessage_message_userID"));
        assertEquals(expectedMap.get(false), actualMap.get("addMessage_message_readReceiptEnabled"));
        assertEquals(expectedMap.get("Dwight Shrute"), actualMap.get("addMessage_principal_name"));
        assertEquals(expectedMap.get("dwishu"), actualMap.get("addMessage_principal_userID"));


    }


    @Test
    public void setStatus() {
        Map<String, String> actualMap = externalMessage.createAttributesMap(inputMessageSetStatus);
        Map<String, String> expectedMap = createExpectedAttributesMapSetStatus();
        assertEquals(expectedMap.get("700"), actualMap.get("setStatus_flowInstanceID"));
        assertEquals(expectedMap.get("9000"), actualMap.get("setStatus_externalID_ID"));
        assertEquals(expectedMap.get("HAL"), actualMap.get("setStatus_externalID_System"));
        assertEquals(expectedMap.get("2"), actualMap.get("setStatus_statusID"));
        assertEquals(expectedMap.get("Upptagen"), actualMap.get("setStatus_statusAlias"));
        assertEquals(expectedMap.get("Michael Scott"), actualMap.get("setStatus_principal_name"));
        assertEquals(expectedMap.get("micsco"), actualMap.get("setStatus_principal_userID"));
    }

}
