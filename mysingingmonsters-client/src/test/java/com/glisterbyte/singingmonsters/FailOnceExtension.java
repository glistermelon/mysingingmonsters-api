package com.glisterbyte.singingmonsters;

import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.extension.*;

public class FailOnceExtension implements TestWatcher, BeforeEachCallback {
    private static volatile boolean failed = false;

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        failed = true;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        assumeTrue(!failed, "Skipping due to previous test failure");
    }

}