package com.quangbui.controller;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.quangbui.service.GoogleDriveService;

import ch.qos.logback.classic.Logger;

@Controller
public class OAuth2Controller {

    @Autowired
    private GoogleDriveService googleDriveService;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    @GetMapping("/Callback")
    public RedirectView handleGoogleRedirect(@RequestParam String code) throws IOException {
		Logger logger = (Logger) LoggerFactory.getLogger(OAuth2Controller.class);
		logger.info("Received code: {}", code);
        GoogleAuthorizationCodeFlow flow = googleDriveService.getAuthorizationFlow();
        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri("http://localhost:8080").execute();
        Credential credential = flow.createAndStoreCredential(response, "user");
        return new RedirectView("/products", true);
    }
}