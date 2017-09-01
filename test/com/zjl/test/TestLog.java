package com.zjl.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {
    @Test
    public void test() {
        Log log = LogFactory.getLog(getClass());
        try {
            int i = 1/0;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        log.fatal("fatal");
    }
}
