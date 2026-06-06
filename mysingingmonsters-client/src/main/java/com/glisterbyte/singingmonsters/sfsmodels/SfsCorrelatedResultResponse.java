package com.glisterbyte.singingmonsters.sfsmodels;

import com.glisterbyte.singingmonsters.localization.LocalizedTextManager;
import com.glisterbyte.singingmonsters.sfsmapping.SfsOptional;
import org.jetbrains.annotations.NotNull;

public abstract class SfsCorrelatedResultResponse extends SfsCorrelatedEventModel {

    public boolean success;

    // These are all different fields I've seen used for error messages

    @SfsOptional
    public String message;

    @SfsOptional
    public String error_msg;

    @SfsOptional
    public String errorMessage;

    @NotNull
    public String getMessage(LocalizedTextManager localizedTextManager) {
        if (message != null) return message;
        if (error_msg != null) return localizedTextManager.getText(error_msg);
        if (errorMessage != null) return errorMessage;
        return "[no message]";
    }

}