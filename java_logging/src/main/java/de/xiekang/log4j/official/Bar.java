package de.xiekang.log4j.official;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class Bar {
    static final Logger logger = LogManager.getLogger(Bar.class.getName());

    public boolean doIt() {
        logger.traceEntry();
        logger.error("Did it again!");
        ThreadContext.put("my test", "test123456");
        return logger.traceExit(false);
    }
}
