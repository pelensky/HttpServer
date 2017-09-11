package com.pelensky.httpserver.Routes;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MethodOptions2Test {
    MethodOptions2 methodOptions2;

    @Before
    public void setUp() {
        methodOptions2 = new MethodOptions2();
    }

    @Test
    public void respondsAllForAllowedOptions() {
        assertThat(methodOptions2.getOptions(), containsString("GET"));
        assertThat(methodOptions2.getOptions(), containsString("OPTIONS"));
    }

    @Test
    public void doesNotRespondWithOptionsThatArentAllowed() {
        assertThat(methodOptions2.getOptions(), not(containsString("HEAD")));
        assertThat(methodOptions2.getOptions(), not(containsString("POST")));
        assertThat(methodOptions2.getOptions(), not(containsString("PUT")));
    }
}
