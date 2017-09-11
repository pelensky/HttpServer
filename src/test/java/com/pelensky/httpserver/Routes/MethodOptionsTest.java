package com.pelensky.httpserver.Routes;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class MethodOptionsTest {
    private MethodOptions methodOptions;

    @Before
    public void setUp() {
        methodOptions = new MethodOptions();
    }

    @Test
    public void respondsAllForOptions() {
        assertThat(methodOptions.getOptions(), containsString("GET"));
        assertThat(methodOptions.getOptions(), containsString("HEAD"));
        assertThat(methodOptions.getOptions(), containsString("POST"));
        assertThat(methodOptions.getOptions(), containsString("OPTIONS"));
        assertThat(methodOptions.getOptions(), containsString("PUT"));

    }
}
