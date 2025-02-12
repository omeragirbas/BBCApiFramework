package com.bbc.sports.baseurl;

import com.bbc.sports.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class BaseUrl {

    public static RequestSpecification spec;

    public static void setUp() {
        String baseUrl = ConfigReader.getProperty("baseUrl");

        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUrl)
                .build();
    }
}
