package com.pelensky.httpserver.Utilities;

import java.util.Objects;

public class CommandLineArgumentParser {

    private final String[] args;

    public CommandLineArgumentParser(String[] args) {
        this.args = args;
    }

    private Integer loopThroughArguments(String arg) {
        Integer offset = 1;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(arg)) {
                return i + offset;
            }
        }
        return null;
    }

    public Integer findPort() {
        Integer index = loopThroughArguments("-p");
        Integer defaultPort = 5000;
        return Objects.equals(index, null) ? defaultPort : Integer.parseInt(args[index]);
    }

    String findDirectory() {
        Integer index = loopThroughArguments("-d");
        String defaultDirectory = "PUBLIC_DIR";
        return Objects.equals(index, null) ? defaultDirectory : args[index];
    }
}

