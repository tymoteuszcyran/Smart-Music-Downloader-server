package pl.tymoteuszborkowski.controllers;

import org.springframework.stereotype.Component;
import pl.tymoteuszborkowski.spotify.SpotifyService;
import pl.tymoteuszborkowski.youtube.Download;
import pl.tymoteuszborkowski.youtube.VideoFilters;
import pl.tymoteuszborkowski.youtube.YouTubeService;

import javax.ws.rs.*;
import javax.ws.rs.Path;

@Component
@Path("api")
public class Endpoint {

    private final YouTubeService service = new YouTubeService();
    private final VideoFilters filters = new VideoFilters();
    private final Download download = new Download();
    private final SpotifyService spotifyDuration = new SpotifyService();

    @Path("mp3")
    @GET
    @Produces("audio/mpeg")
    public void getMp3(@QueryParam("title") String title,
                           @QueryParam("artist") String artist) {
//        List<SearchResult> resultList = service.searchVideos(artist + title);
//        List<Video> allVideos = service.getVideos(resultList);
//        List<Video> sortedByQualityList = filters.sortByQuality(allVideos);
//        Map<Long, Video> mapWithDuration = filters.sortByLength(sortedByQualityList);
//        String bodyJSON = spotifyDuration.getSpotifyResponse(artist, title);
//        long originalTrackDuration = spotifyDuration.findTrackDuration(bodyJSON);
//        Video theBestVideo = filters.getTheBestVideo(mapWithDuration, originalTrackDuration);
//        String url = download.getURL(theBestVideo);
//        download.downloadMp3(url);
//        String fileDirectory = download.fileDirectory(theBestVideo);
//
//
//        StreamingOutput stream = output -> {
//            java.nio.file.Path path = Paths.get(fileDirectory);
//            Files.copy(path, output);
//            output.flush();
//        };
//
//        return Response
//                .ok()
//                .status(200)
//                .header("filename", FilenameUtils.getName(fileDirectory))
//                .entity(stream)
//                .build();
    }


}
