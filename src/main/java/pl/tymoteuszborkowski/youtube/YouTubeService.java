package pl.tymoteuszborkowski.youtube;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.repackaged.com.google.common.base.Joiner;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class YouTubeService {

    private static final String API_KEY = "AIzaSyCN4MUujeEi1dqDzQ646iVRv3VWmTsQH_w";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 6;

    private final YouTube youTube;

    public YouTubeService() {
        youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), request -> {
        }).setApplicationName("smart-mp3").build();
    }


    public List<SearchResult> searchVideos(String query){
        List<SearchResult> responseList = new ArrayList<>();
        try {
            YouTube.Search.List search = youTube.search().list("id, snippet");
                search.setKey(API_KEY);
                search.setQ(query);
                search.setType("video");
                search.setFields("items(id/kind,id/videoId,snippet/title, snippet/thumbnails/default/url)");
                search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            SearchListResponse searchListResponse = search.execute();
            responseList = searchListResponse.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseList;
    }


    public List<Video> getVideos(List<SearchResult> resultList){
        List<Video> videos = new ArrayList<>();
        List<String> videosIds = new ArrayList<>();

        try {
            videosIds.addAll(resultList.stream().map(result -> result.getId().getVideoId()).collect(Collectors.toList()));

            Joiner stringJoiner = Joiner.on(',');
            String videoId = stringJoiner.join(videosIds);

            YouTube.Videos.List listVideosRequest = youTube.videos().list("snippet, contentDetails").setId(videoId);
            listVideosRequest.setKey(API_KEY);
            VideoListResponse listResponse = listVideosRequest.execute();

            videos = listResponse.getItems();

        } catch (IOException e) {
            // TODO: 31.08.2016 implement friendly message
            e.printStackTrace();
        }
        return videos;

    }

}
