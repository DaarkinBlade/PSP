import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class GestorPeticionHTTPBuilder {
    public static void main(String[] args) {
        try {
            String esquema = "https://";
            String servidor = "dle.rae.es/";
            String recurso = new String("Campeón").toLowerCase();
            GestorPeticionHTTPBuilder gestor = new GestorPeticionHTTPBuilder();
            int codigoEstado = gestor.almacenarPagina(esquema, servidor, recurso, "C:\\Users\\Marco\\OneDrive\\descargas\\resultado.html");
            if (codigoEstado == HttpURLConnection.HTTP_OK) {
                System.out.println("Descarga finalizada");
            } else {
                System.err.println("Error" + codigoEstado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int almacenarPagina (String esquema, String servidor, String recurso, String path) throws IOException, InterruptedException {
        recurso = URLEncoder.encode(recurso, StandardCharsets.UTF_8);
        String direccion = esquema + servidor + recurso;
        HttpClient httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(direccion))
                .headers("Content-type", "text/plain")
                .setHeader("User-Agent", "Mozilla/5.0")
                .build();
        HttpResponse<Path> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofFile(Path.of(path)));
        return response.statusCode();
    }
}