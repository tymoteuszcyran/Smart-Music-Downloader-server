package pl.tymoteuszborkowski;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tymoteuszborkowski.youtube.Download;
import pl.tymoteuszborkowski.youtube.VideoFilters;
import pl.tymoteuszborkowski.youtube.YouTubeFactory;
import pl.tymoteuszborkowski.youtube.YouTubeService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DownloadTest {

    @Test
    public void downloadVideoTest() throws IOException, InterruptedException {
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target("http://10.0.2.2:8080/api/mp3");
        final UriBuilder uriBuilder = target.getUriBuilder();

        uriBuilder.queryParam("title", "Muna");
        uriBuilder.queryParam("artist", "Winterbreak");
        final URI uri = uriBuilder.build();
        System.out.println(uri);
        final WebTarget webTarget = client.target(uri);
        Response response = webTarget.request().get();

        System.out.println(response.getStatus());



    }

}
