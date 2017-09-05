package com.pelensky.httpserver;

import java.util.ArrayList;
import java.util.List;

class Response {

        private static List<ResponseCommand> selections() {
            List<ResponseCommand> selectionList = new ArrayList<>();
            selectionList.add(new GetCommand());
            selectionList.add(new PostCommand());
            selectionList.add(new PutCommand());
            selectionList.add(new HeadCommand());
            return selectionList;
        }

        static String findCommand(String input) {
            for (ResponseCommand selection : selections()) {
                if (selection.respondsTo(input)) {
                    return selection.execute(input);
                }
            }
            return Status.codes().get(404);
        }
    }
