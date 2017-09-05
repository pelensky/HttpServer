package com.pelensky.httpserver;

public class PutCommand implements ResponseCommand {
    @Override
    public String execute() {
        return Status.codes().get(200);
    }

    @Override
    public boolean respondsTo(String input) {
        return input.startsWith("PUT /");
    }
}
