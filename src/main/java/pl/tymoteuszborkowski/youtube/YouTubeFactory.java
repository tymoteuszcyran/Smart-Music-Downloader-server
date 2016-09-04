package pl.tymoteuszborkowski.youtube;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

public class YouTubeFactory {

    private final YouTube youTube;

    public YouTubeFactory(){
        youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), request -> {
        }).setApplicationName("smart-mp3").build();
    }


    public YouTubeService createYouTubeService(){
        return new YouTubeService(youTube);
    }
}
