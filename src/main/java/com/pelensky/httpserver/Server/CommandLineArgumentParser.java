package com.pelensky.httpserver.Server;

import java.util.Objects;

public class CommandLineArgumentParser {

    private String[] args;
    private final int defaultPort = 5000;
    private final String defaultDirectory = "PUBLIC_DIR";

    public CommandLineArgumentParser(String[] args) {
        this.args = args;
    }

    private Integer loopThroughArguments(String arg) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(arg)) {
                return i + 1;
            }
        }
        return null;
    }

    public int findPort() {
        Integer index = loopThroughArguments("-p");
        return Objects.equals(index, null) ? defaultPort : Integer.parseInt(args[index]);
    }

    public String findDirectory() {
        Integer index = loopThroughArguments("-d");
        return Objects.equals(index, null) ? defaultDirectory : args[index];
    }
}

