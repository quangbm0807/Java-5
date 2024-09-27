package com.quangbui.service;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quangbui.model.Province;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class ProvinceService {
	public static void main(String[] args) {
		ProvinceService ps = new ProvinceService();
		 List<Province> list =  ps.getAllProvinces();
         System.out.print(list);
	}
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper;

    public ProvinceService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<Province> getAllProvinces() {
        try {
            String apiUrl = "https://api-vietnam-quangbui.up.railway.app/province/all";
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .build();
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            Province[] provinces = objectMapper.readValue(jsonData, Province[].class);

            return Arrays.asList(provinces);
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
