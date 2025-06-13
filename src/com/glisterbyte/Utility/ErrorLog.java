package com.glisterbyte.Utility;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorLog {
    public final static Logger NetUtilErrorLogger = Logger.getLogger(ErrorLog.class.getName());
    public static void logError(Exception ex) {
        NetUtilErrorLogger.log(Level.SEVERE, "Error", ex);
    }
}
