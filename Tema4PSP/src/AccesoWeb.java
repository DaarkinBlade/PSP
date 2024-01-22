import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AccesoWeb {
    public static void main(String[] args) throws IOException {
        URL url = new URL ("https://politecnicomalaga.com");
        URLConnection conexionURL = url.openConnection();
    }
}
