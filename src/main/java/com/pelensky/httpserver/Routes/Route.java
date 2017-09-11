package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Response.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Route {
    String route();

    default String call(String method) {
        switch (method) {
            case "GET":
                return this.get();
            case "HEAD":
                return this.head();
            case "POST":
                return this.post();
            case "OPTIONS":
                return this.options();
            case "PUT":
                return this.put();
            default:
                return Status.codes().get(404);
        }
    }

    default String get() {
        return Status.codes().get(200);
    };

    default String head() {
        return Status.codes().get(405);
    }

    default String post() {
        return Status.codes().get(405);
    }

    default String options() {
        return (optionsCode().equals(Status.codes().get(405))) ? optionsCode() : optionsCode() + "\n" + "Allow: " + getOptions();
    }

    default String optionsCode() {
        return Status.codes().get(405);
    }

    default String put() {
        return Status.codes().get(405);
    }

    default String getOptions() {
        List<String> options = new ArrayList<>();
        options.add((!get().equals(Status.codes().get(405))) ? "GET" : null);
        options.add((!head().equals(Status.codes().get(405))) ? "HEAD" : null);
        options.add((!post().equals(Status.codes().get(405))) ? "POST" : null);
        options.add((!optionsCode().equals(Status.codes().get(405))) ? "OPTIONS" : null);
        options.add((!put().equals(Status.codes().get(405))) ? "PUT" : null);
        return options.stream().filter(Objects::nonNull).collect(Collectors.joining(","));
    }

}
