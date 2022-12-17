package me.fabio;

import com.google.gson.*;
import me.fabio.enums.Action;
import me.fabio.enums.Endpoint;
import me.fabio.objects.Carrot;
import me.fabio.objects.Delivery;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class CarrotStorage {

    private final String key;
    private final String url;
    private final String zoneName;

    private final HttpClient client;
    public static final Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    public CarrotStorage(String key, String zoneName, Endpoint endpoint) {
        this.key = key;
        this.url = endpoint.buildUrl();
        this.zoneName = zoneName;

        this.client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
    }

    public Delivery upload(byte[] data, String path, String fileName) {
        return sendRequest(path + "/" + fileName, data, Action.UPLOAD);
    }

    public Delivery download(String path, String fileName) {
        return sendRequest(path + "/" + fileName, null, Action.DOWNLOAD);
    }

    public Delivery delete(String path, String fileName) {
        return sendRequest(path + "/" + fileName, null, Action.DELETE);
    }

    public Optional<List<Carrot>> list(String path) {
        if (path.isBlank()) {
            path = "/";
        } else if (!path.endsWith("/")) {
            path += "/";
        }

        Delivery delivery = sendRequest(path, null, Action.LIST);
        if (delivery.isSuccessful()) {
            String str = delivery.toObject();

            JsonArray array = JsonParser.parseString(str).getAsJsonArray();
            List<Carrot> list = new ArrayList<>();
            for (JsonElement ele : array) {
                Carrot carrot;
                try {
                    carrot = gson.fromJson(ele, Carrot.class);
                } catch (JsonSyntaxException ex) {
                    continue;
                }
                list.add(carrot);
            }

            return Optional.of(list);
        }

        return Optional.empty();
    }

    private Delivery sendRequest(String method, byte[] data, Action action) {
        HttpRequest.BodyPublisher publisher = data != null
                ? HttpRequest.BodyPublishers.ofByteArray(data)
                : HttpRequest.BodyPublishers.noBody();

        HttpRequest request;
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .method(action.getMethod(), publisher)
                    .header("AccessKey", key)
                    .timeout(Duration.ofSeconds(5))
                    .uri(new URI(url + "/" + zoneName + "/" + method));

            if (data != null) builder.setHeader("content-type", "application/octet-stream");

            request = builder.build();
        } catch (URISyntaxException ex) {
            return new Delivery(generateJson(ex.getMessage(), false), 400, null, Void.TYPE);
        }

        try {
            int code;
            if (action.equals(Action.DOWNLOAD)) {
                HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
                code = response.statusCode();
                if (HttpUtils.successful(code)) {
                    return new Delivery(generateJson("File downloaded", true), code, response.body(), byte[].class);
                }
            } else {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                code = response.statusCode();
                if (HttpUtils.successful(code)) {
                    return new Delivery(generateJson("Success", true), code, response.body(), String.class);
                }
            }

            return new Delivery(generateJson("Bad request (probably)", false), code, null, Void.TYPE);
        } catch (IOException | InterruptedException ex) {
            return new Delivery(generateJson(ex.getMessage(), false), 400, null, Void.TYPE);
        }
    }

    private String generateJson(String message, boolean success) {
        JsonObject obj = new JsonObject();
        obj.add("success", new JsonPrimitive(success));
        obj.add("message", new JsonPrimitive(message));
        return obj.toString();
    }
}
