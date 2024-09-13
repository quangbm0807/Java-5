
package com.quangbui.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quangbui.model.Province;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProvinceService {

	private final OkHttpClient client = new OkHttpClient();
	private final ObjectMapper objectMapper;

	public ProvinceService() {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	public List<Province> getAllProvinces() {
		try {
			String apiUrl = "https://provinces.open-api.vn/api/?depth=2";
			Request request = new Request.Builder()
					.url(apiUrl)
					.build();
			Response response = client.newCall(request).execute();
			String jsonData = response.body().string();
			System.out.println(jsonData);
			return objectMapper.readValue(jsonData, new TypeReference<List<Province>>() {});
		} catch (IOException e) {
			e.printStackTrace();

			return List.of();
		}
	}
}