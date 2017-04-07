package pl.tymoteuszborkowski.youtube;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class YouTubeServiceTest {


    @Test
    public void searchingVideoTest(){
        YouTubeService service = new YouTubeService();

        List<Video> videos = service.searchVideos("Adele under the bridge");
        for (Video video : videos) {
            System.out.println(video.getContentDetails().getDuration());
        }
    }

}
