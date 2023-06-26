package de.telefonica.talend.utils;

import org.junit.jupiter.api.Test;

import static de.telefonica.talend.utils.Utils.updateResponse;

class ExecuteProcessResponseTest {

    @Test
    void updateResponseTest() {
        updateResponse(
                "C:\\dev\\workspace\\AppsoluteUTS\\src\\test\\resources\\executeProcessResponse.xml",
                "C:\\dev\\workspace\\AppsoluteUTS\\src\\test\\resources\\response.xml");
    }
}