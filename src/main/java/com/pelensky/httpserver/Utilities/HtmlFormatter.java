package com.pelensky.httpserver.Utilities;

public class HtmlFormatter {
    public String format(String title, String[] directoryContents) {
        return openingHtml(title) + formatLinks(directoryContents) + closingHtml();
    }

    private String openingHtml(String title) {
        return "<!DOCTYPE html>" + System.lineSeparator() +
                    "<HTML>" + System.lineSeparator() +
                    "<HEAD>" + System.lineSeparator() +
                    "<TITLE>" + title + "</TITLE>" + System.lineSeparator() +
                    "</HEAD>" + System.lineSeparator() +
                    "<BODY>" + System.lineSeparator();
    }

    private String formatLinks(String[] directoryContents) {
        StringBuilder links = new StringBuilder();
        if (directoryContents != null) {
            for (String file : directoryContents) {
                links.append("<a href=\"/").append(file).append("\">").append(file).append("</a>").append("<br>").append(System.lineSeparator());
            }
        }
        return String.valueOf(links);
    }

    private String closingHtml() {
        return "</BODY>" + System.lineSeparator() +
                "</HTML>";
    }
}
