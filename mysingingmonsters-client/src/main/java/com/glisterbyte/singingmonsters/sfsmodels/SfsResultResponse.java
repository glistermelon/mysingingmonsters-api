package com.glisterbyte.singingmonsters.sfsmodels;

import com.glisterbyte.singingmonsters.localization.LocalizedTextManager;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import org.jetbrains.annotations.NotNull;

public class SfsResultResponse extends SfsEventModel {

    public boolean success;

    @SfsOptional
    public String message;

    @SfsOptional
    public String error_msg;

    @NotNull
    public String getMessage(LocalizedTextManager localizedTextManager) {
        if (message != null) return message;
        if (error_msg != null) return localizedTextManager.getText(error_msg);
        return "[no message]";
    }

}
