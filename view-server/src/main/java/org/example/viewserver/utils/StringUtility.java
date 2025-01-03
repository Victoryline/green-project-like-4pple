package org.example.viewserver.utils;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class StringUtility {
    public List<String> split(String input, String delimiter) {
        if (input == null || input.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(input.split(delimiter));
    }
}
