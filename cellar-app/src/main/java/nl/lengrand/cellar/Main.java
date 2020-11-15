package nl.lengrand.cellar;

import io.helidon.microprofile.server.Server;

import java.io.IOException;

public class Main {

    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        Server.create().start();
    }
}
