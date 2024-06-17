package de.xiekang.talend.beans;

import org.junit.jupiter.api.Test;


import java.util.Map;

class ValueMappingUtilsTest {

    @Test
    void createMappingTest() {
        System.out.println("Test 1: " + ValueMappingUtils.createMapping("/landescode.json"));
    }

    @Test
    void findStringValueOnKey() {
        System.out.println("Test 2: " +ValueMappingUtils.findStringValueOnKey("/landescode.json", "AX"));
    }

    @Test
    void findIntKeyOnValue() {
        System.out.println("Test 3: " +ValueMappingUtils.findStringKeyOnValue("/landescode.json", "DEU"));
    }

    @Test
    void findStringValueOnKey1() {
        System.out.println("Test 4: " +ValueMappingUtils.findStringValueOnKey("/landescode.json", "DE"));
    }
}