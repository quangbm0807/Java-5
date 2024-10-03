package com.quangbui.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;

@Service
public class GoogleDriveService {

	private static final String APPLICATION_NAME = "";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";
	private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/drive.file");
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	private Drive driveService;
	private GoogleAuthorizationCodeFlow flow;

	public GoogleDriveService() throws IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		this.driveService = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		this.flow = initializeFlow(HTTP_TRANSPORT);
	}

	private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	private GoogleAuthorizationCodeFlow initializeFlow(NetHttpTransport transport) throws IOException {
		InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		return new GoogleAuthorizationCodeFlow.Builder(transport, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();
	}

	public GoogleAuthorizationCodeFlow getAuthorizationFlow() {
		return flow;
	}

	public String uploadFile(MultipartFile file) {
		try {
			com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();

			String originalFileName = file.getOriginalFilename();

			String timeStamp = String.valueOf(System.currentTimeMillis());

			String fileExtension = "";
			int dotIndex = originalFileName.lastIndexOf('.');
			if (dotIndex > 0) {
				fileExtension = originalFileName.substring(dotIndex);
				originalFileName = originalFileName.substring(0, dotIndex);
			}

			String newFileName = originalFileName + "_" + timeStamp + fileExtension;

			fileMetadata.setName(newFileName);

			InputStreamContent mediaContent = new InputStreamContent(file.getContentType(), file.getInputStream());

			com.google.api.services.drive.model.File uploadedFile = driveService.files()
					.create(fileMetadata, mediaContent).setFields("id").execute();

			Permission permission = new Permission();
			permission.setType("anyone");
			permission.setRole("reader");

			driveService.permissions().create(uploadedFile.getId(), permission).execute();

			return uploadedFile.getId();
		} catch (IOException e) {
			throw new RuntimeException("Failed to upload file to Google Drive", e);
		}
	}
	public void deleteFile(String fileId) {
	    try {
	        driveService.files().delete(fileId).execute();
	        System.out.println("File deleted successfully.");
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to delete file from Google Drive", e);
	    }
	}

}