package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HttpClientRunner {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest.class.getClassLoader().getResourceAsStream("pro100.json");
         HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000"))
                 .version(HttpClient.Version.HTTP_1_1)
                 .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> response2 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> response3 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response3.get().headers());
        System.out.println(response3.get().body());
    }

}
