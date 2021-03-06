package io.github.delirius325.jmeter.config.livechanges;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.delirius325.jmeter.config.livechanges.api.App;

import static junit.framework.TestCase.assertTrue;

public class TestAppServer {
    private static int PORT = 8080;
    private static String APP_ADDRESS = "http://localhost:" + PORT;

    private App server;

    @Before
    public void startTest() throws Exception {
        this.server = new App(PORT);
        this.server.start();
        this.server.setupRoutes();
        System.out.println("------------------------ Server listening! ---------------------------");
    }

    @Test
    public void testServerConnectivity() throws Exception {
        HttpResponse<String> response = Unirest.get(APP_ADDRESS + "/test/connectivity").asString();
        assertTrue(response.getBody().contains("connected"));
    }

    @After
    public void stopTest() throws Exception {
        this.server.stop();
    }
}
