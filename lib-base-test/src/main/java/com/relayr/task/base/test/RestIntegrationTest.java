package com.relayr.task.base.test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import javax.servlet.ServletContext;
/**
 * @author Nassim MOUALEK
 * @since 28/11/2020
 * @licence MIT
 */
public class RestIntegrationTest {
    @LocalServerPort
    protected int port;
    @Autowired
    protected ServletContext servletContext;

    @BeforeAll
    public void setup() {
        RestAssured.port = port;
        RestAssured.basePath = servletContext.getContextPath();
    }
}
