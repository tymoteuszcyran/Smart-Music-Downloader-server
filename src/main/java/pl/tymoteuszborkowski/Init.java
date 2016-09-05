package pl.tymoteuszborkowski;

import pl.tymoteuszborkowski.youtube.YouTubeFactory;
import pl.tymoteuszborkowski.youtube.YouTubeService;

public class Init {

    private final YouTubeFactory youTubeFactory = new YouTubeFactory();
    private final YouTubeService youTubeService = youTubeFactory.createYouTubeService();



}
