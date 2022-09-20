package aman.se;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ExternalMessage implements Attributes {
    @Override
    public Map<String, String> createAttributesMap(String inputMessage) {
        Map<String, String> map = Arrays.stream(inputMessage.split(","))
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
        return map;
    }
}
