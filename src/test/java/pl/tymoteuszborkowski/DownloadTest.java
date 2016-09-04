package pl.tymoteuszborkowski;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tymoteuszborkowski.youtube.Download;
import pl.tymoteuszborkowski.youtube.VideoFilters;
import pl.tymoteuszborkowski.youtube.YouTubeFactory;
import pl.tymoteuszborkowski.youtube.YouTubeService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DownloadTest {

    @Test
    public void downloadVideoTest(){
        YouTubeFactory factory = new YouTubeFactory();
        YouTubeService service = factory.createYouTubeService();
        VideoFilters filters = new VideoFilters();
        Download download = new Download();

        List<SearchResult> resultList = service.searchVideos("Oddałbym");
        List<Video> videos = service.getVideos(resultList);

        URL url = null;
        try {
            url = filters.getURL(videos.get(0));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(url != null){
            download.downloadVideo(url);
        }


    }

}
