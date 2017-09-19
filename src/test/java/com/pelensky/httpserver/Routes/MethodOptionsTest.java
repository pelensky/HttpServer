package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class MethodOptionsTest {
    private MethodOptions methodOptions;

    @Before
    public void setUp() {
        methodOptions = new MethodOptions();
    }

    @Test
    public void respondsAllForOptions() throws IOException, NoSuchAlgorithmException {
        Request request = new Request("OPTIONS", "method_options", null,  null, null, null, null);
        assertThat(methodOptions.getOptions(request), containsString("GET"));
        assertThat(methodOptions.getOptions(request), containsString("HEAD"));
        assertThat(methodOptions.getOptions(request), containsString("POST"));
        assertThat(methodOptions.getOptions(request), containsString("OPTIONS"));
        assertThat(methodOptions.getOptions(request), containsString("PUT"));

    }
}
