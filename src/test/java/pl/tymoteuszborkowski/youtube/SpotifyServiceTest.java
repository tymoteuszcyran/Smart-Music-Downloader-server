package pl.tymoteuszborkowski.youtube;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tymoteuszborkowski.spotify.SpotifyService;

@SpringBootTest
public class SpotifyServiceTest {

    @Test
    public void spotifyResponseTest(){
        SpotifyService spotifyService = new SpotifyService();
        Long duration = spotifyService.searchForTrackDuration("Nullo", "Nie mogę tak żyć");
        System.out.println(duration);

    }

}
