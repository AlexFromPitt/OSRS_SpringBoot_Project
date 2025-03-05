package com.osrs_springboot_project.osrs_springboot_project.converters;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PriceConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        source = source.replace(",", "");
        Character c = null;
        List<Character> charList = List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        int start = 0;
        int end = source.length();
        boolean multiplyByMillion = false;
        boolean multiplyByThousand = false;
        Double value = 0.0;

        try {
            // Check to see if the string needs modified.
            // Start with the beginning:
            boolean valid = false;
            while (!valid) {
                c = source.charAt(start);
                if (charList.contains(c)) valid = true;
                else start++;
            }

            // Now check the end:
            valid = false;
            while (!valid) {
                c = Character.toLowerCase(source.charAt(end-1));
                if (c == 'm') {
                    multiplyByMillion = true;
                    end--;
                }
                else if (c == 'k') {
                    multiplyByThousand = true;
                }
                else if (charList.contains(c)) valid = true;
            }

            if (multiplyByMillion) {
                value = Double.parseDouble(source.substring(start, end));
                System.out.println(value);
                value = value * 1000000;
                return value.intValue();
            } else if (multiplyByThousand) {
                value = Double.parseDouble(source.substring(start, end));
                value = value * 1000;
                return value.intValue();
            } else {
                return Integer.parseInt(source.substring(start, end));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer format: " + source);
        }
    }
}
