package pl.tymoteuszborkowski.controllers;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import pl.tymoteuszborkowski.spotify.SpotifyDuration;
import pl.tymoteuszborkowski.youtube.Download;
import pl.tymoteuszborkowski.youtube.VideoFilters;
import pl.tymoteuszborkowski.youtube.YouTubeService;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

@Component
@Path("api")
public class Endpoint {

    private final YouTubeService service = new YouTubeService();
    private final VideoFilters filters = new VideoFilters();
    private final Download download = new Download();
    private final SpotifyDuration spotifyDuration = new SpotifyDuration();

    @Path("mp3")
    @GET
    @Produces("audio/mpeg")
    public Response getMp3(@QueryParam("title") String title,
                           @QueryParam("artist") String artist) {
        List<SearchResult> resultList = service.searchVideos(artist + title);
        List<Video> allVideos = service.getVideos(resultList);
        List<Video> sortedByQualityList = filters.sortByQuality(allVideos);
        Map<Long, Video> mapWithDuration = filters.sortByLength(sortedByQualityList);
        String bodyJSON = spotifyDuration.getSpotifyResponse(artist, title);
        long originalTrackDuration = spotifyDuration.findTrackDuration(bodyJSON);
        Video theBestVideo = filters.getTheBestVideo(mapWithDuration, originalTrackDuration);
        String url = download.getURL(theBestVideo);
        download.downloadMp3(url);
        String fileDirectory = download.fileDirectory(theBestVideo);


        StreamingOutput stream = output -> {
            java.nio.file.Path path = Paths.get(fileDirectory);
            Files.copy(path, output);
            output.flush();
        };

        return Response
                .ok()
                .status(200)
                .header("filename", FilenameUtils.getName(fileDirectory))
                .entity(stream)
                .build();
    }


}
