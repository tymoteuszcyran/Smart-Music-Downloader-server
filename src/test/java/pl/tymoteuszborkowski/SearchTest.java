package pl.tymoteuszborkowski;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tymoteuszborkowski.spotify.SpotifyDuration;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {

    @Test
    public void searchTest() throws IOException {
        SpotifyDuration spotifyDuration = new SpotifyDuration();
        spotifyDuration.getSpotifyResponse("Muna", "Winterbreak");
    }
}
