package com.glisterbyte.Network.ClientException;

public class FetchFailed extends RuntimeException {
    public FetchFailed(Exception ex) {
        super(ex);
    }
}
