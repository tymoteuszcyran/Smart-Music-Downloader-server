package pl.tymoteuszborkowski;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tymoteuszborkowski.youtube.VideoFilters;
import pl.tymoteuszborkowski.youtube.YouTubeFactory;
import pl.tymoteuszborkowski.youtube.YouTubeService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoFiltersTest {

	@Test
	public void searchVideos() {
		YouTubeFactory factory = new YouTubeFactory();
		YouTubeService service = factory.createYouTubeService();
		VideoFilters filters = new VideoFilters();

		List<SearchResult> resultList = service.searchVideos("Winterbreak");
		List<Video> videos = service.getVideos(resultList);

		filters.sortByLength(videos);



	}



}
