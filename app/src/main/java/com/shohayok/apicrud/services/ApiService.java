package com.shohayok.apicrud.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService {
    private OkHttpClient client;

    public ApiService() {
        this.client = new OkHttpClient();
    }

    public JSONObject postData(String url,JSONObject payload) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(payload.toString(), JSON);
        // Create the request
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response)  {
                // Handle response
                if (response.isSuccessful()) {
                    try {
                        return response.toString();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }


        });
    }

}