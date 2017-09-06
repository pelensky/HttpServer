package com.pelensky.httpserver;

import Validations.Options;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class OptionsTest {

    @Test
    public void getsTheCorrectRoutes() {
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", Options.getOptions("/method_options"));
    }

    @Test
    public void getsRouteForOtherOption() {
        assertEquals("GET,OPTIONS", Options.getOptions("/method_options2"));
    }

    @Test
    public void defaultsToAll() {
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", Options.getOptions("/"));
    }
}
