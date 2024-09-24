import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlLearn {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.google.com");
        URLConnection urlConnection = url.openConnection();

        System.out.println();
    }

}
