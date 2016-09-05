package pl.tymoteuszborkowski.spotify;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SpotifyDuration {

    private static final String SPOTIFY_GET = "https://api.spotify.com/v1/search?q=";
    private static final String TRACK_TYPE = "&type=track";
    private static final String SPACE = "%20";
    private static final String RESPONSE_LIMIT = "&limit=1";
    private static final String DURATION_KEY = "&duration_ms";
    private static final String DURATION_INDEX = "duration_ms";
    private static final String COMMA = ",";

    public String getSpotifyResponse(String artist, String title) throws IOException {
        artist = artist.replace(" ", SPACE);
        title = title.replace(" ", SPACE);
        String query = SPOTIFY_GET + artist + "%20" + title + TRACK_TYPE + RESPONSE_LIMIT + DURATION_KEY;

        URL url = new URL(query);
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;

        return IOUtils.toString(in, encoding);
    }

    public long findTrackDuration(final String body) {
        long seconds = 0;
        if (body.contains(DURATION_INDEX)) {
            int indexOfDuration = body.indexOf(DURATION_INDEX);
            int indexOfComma = body.indexOf(COMMA, indexOfDuration);
            String durationResponse = body.substring(indexOfDuration, indexOfComma);
            String secondsStr = durationResponse.replaceAll("[^\\d.]", "");

            seconds = Long.parseLong(secondsStr);
            seconds /= 1000;
        }

        return seconds;
    }


}
