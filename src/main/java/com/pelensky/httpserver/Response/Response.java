package com.pelensky.httpserver.Response;

import com.pelensky.httpserver.Request;
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

        public static String findCommand(Request request) {
            for (ResponseCommand selection : selections()) {
                if (selection.respondsTo(request.getMethod())) {
                    return selection.execute(request);
                }
            }
            return null;
        }
    }
