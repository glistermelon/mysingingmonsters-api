package com.glisterbyte.singingmonsters.exceptions;

import com.glisterbyte.singingmonsters.localization.LocalizedTextManager;
import com.glisterbyte.singingmonsters.sfsmodels.SfsCorrelatedResultResponse;
import com.glisterbyte.singingmonsters.sfsmodels.SfsResultResponse;

public class ActionFailedException extends ClientException {

    private String serverMessage = "";

    public ActionFailedException(SfsResultResponse response, LocalizedTextManager localizedTextManager) {
        super(response.getMessage(localizedTextManager));
        serverMessage = response.getMessage(localizedTextManager);
    }

    public ActionFailedException(SfsCorrelatedResultResponse response, LocalizedTextManager localizedTextManager) {
        super(response.getMessage(localizedTextManager));
        serverMessage = response.getMessage(localizedTextManager);
    }

    public ActionFailedException(String message) {
        super(message);
    }

    public String getServerMessage() {
        return serverMessage;
    }

}
