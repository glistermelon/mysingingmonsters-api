package com.glisterbyte;

import com.glisterbyte.Network.Client;

import java.io.IOException;

public class
Test {
    public static void main(String[] args) throws InterruptedException, IOException {
        Client client = new Client();
        client.connect(false);
    }
}
