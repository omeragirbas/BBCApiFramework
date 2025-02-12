package com.bbc.sports.hooks;

import io.cucumber.java.Before;

import static com.bbc.sports.baseurl.BaseUrl.setUp;

public class Hooks {

    @Before ("@api")
    public void beforeApi(){
        setUp();
    }
}
