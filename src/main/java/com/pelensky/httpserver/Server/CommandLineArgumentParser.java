package com.pelensky.httpserver.Server;

import java.util.Objects;

class CommandLineArgumentParser {

    private String[] args;
    private final Integer defaultPort = 5000;
    private final String defaultDirectory = "PUBLIC_DIR";

    CommandLineArgumentParser(String[] args) {
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

    Integer findPort() {
        Integer index = loopThroughArguments("-p");
        return Objects.equals(index, null) ? defaultPort : Integer.parseInt(args[index]);
    }

    String findDirectory() {
        Integer index = loopThroughArguments("-d");
        return Objects.equals(index, null) ? defaultDirectory : args[index];
    }
}

