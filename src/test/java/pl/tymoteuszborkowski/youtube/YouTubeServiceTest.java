package pl.tymoteuszborkowski.youtube;

import com.google.api.services.youtube.model.SearchResult;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class YouTubeServiceTest {


    @Test
    public void searchingVideoTest(){
        YouTubeService service = new YouTubeService();

        List<SearchResult> searchResults = service.searchVideos("Adele Water under the bridge");

        for(SearchResult searchResult : searchResults){
            System.out.println(searchResult.getSnippet().getTitle());
        }
    }

}
