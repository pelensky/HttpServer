package com.pelensky.httpserver.Utilities;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingTool {
    private final static Logger LOGGER = Logger.getLogger(LoggingTool.class.getName());

    public static void setUp() throws IOException {
        LOGGER.setUseParentHandlers(false);
        createLogFile();
        formatLogFile();
    }

    private static void createLogFile() throws IOException {
        Files.createDirectories(Paths.get("./logs/"));
        try {
            Files.createFile(Paths.get("./logs/serverLogs.log"));
        } catch (FileAlreadyExistsException ignored) { }
    }

    private static void formatLogFile() throws IOException {
        FileHandler logFileHandler = new FileHandler("./logs/serverLogs.log");
        logFileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(logFileHandler);
    }

    public static void logRequest(String request) {
        LOGGER.info(request);
    }

    public static byte[] showLogs() throws IOException {
        return Files.readAllBytes(Paths.get("./logs/serverLogs.log"));
    }

}
