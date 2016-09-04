package pl.tymoteuszborkowski.spotify;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SpotifyDuration {

    private static final String SPOTIFY_GET = "https://api.spotify.com/v1/search?q=";
    private static final String QUERY_TYPE_TRACK = "&type=track";
    private static final String SPACE = "%20";
    private static final String KEY_LIMIT = "&limit=1";
    private static final String KEY_DURATION = "&duration_ms";

    public String getSpotifyResponse(String artist, String title) throws IOException {
        artist = artist.replace(" ", SPACE);
        title = title.replace(" ", SPACE);
        String query = SPOTIFY_GET + artist + "%20" + title + QUERY_TYPE_TRACK + KEY_LIMIT + KEY_DURATION;
        System.out.println("FULL QUERY: " + query);

        URL url = new URL(query);
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;

        return IOUtils.toString(in, encoding);
    }


}
