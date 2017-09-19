package com.pelensky.httpserver.Routes;

import com.pelensky.httpserver.Request.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MethodOptions2Test {
    private MethodOptions2 methodOptions2;
    private Request request;

    @Before
    public void setUp() {
        methodOptions2 = new MethodOptions2();
        request = new Request("OPTIONS", "method_options2", null, null, null, null, null);
    }

    @Test
    public void respondsAllForAllowedOptions() throws IOException, NoSuchAlgorithmException {
        assertThat(methodOptions2.getOptions(request), containsString("GET"));
        assertThat(methodOptions2.getOptions(request), containsString("OPTIONS"));
    }

    @Test
    public void doesNotRespondWithOptionsThatArentAllowed() throws IOException, NoSuchAlgorithmException {
        assertThat(methodOptions2.getOptions(request), not(containsString("HEAD")));
        assertThat(methodOptions2.getOptions(request), not(containsString("POST")));
        assertThat(methodOptions2.getOptions(request), not(containsString("PUT")));
    }
}
