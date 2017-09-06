package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.RequestTypes.*;

import java.util.ArrayList;
import java.util.List;

public class Response {

        private static List<ResponseCommand> selections() {
            List<ResponseCommand> selectionList = new ArrayList<>();
            selectionList.add(new GetCommand());
            selectionList.add(new PostCommand());
            selectionList.add(new PutCommand());
            selectionList.add(new HeadCommand());
            selectionList.add(new OptionsCommand());
            return selectionList;
        }

        public static String findCommand(List<String> inputs) {
            String input = String.join("\n", inputs);
            for (ResponseCommand selection : selections()) {
                if (selection.respondsTo(input)) {
                    return selection.execute(input);
                }
            }
            return null;
        }
    }
