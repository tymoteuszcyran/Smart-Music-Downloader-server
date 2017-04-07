package pl.tymoteuszborkowski.spotify;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.tymoteuszborkowski.rest.Items;

public class SpotifyService {

    private static final String SPOTIFY_QUERY = "https://api.spotify.com/v1/search";
    private static final String QUERY_INFO = "&type=track&limit=1&duration_ms";
    private static final String SPACE = "%20";
    private static final String DURATION_INDEX = "duration_ms";
    private static final String COMMA = ",";



    public String getSpotifyResponse(String artist, String title){


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SPOTIFY_QUERY)
                .queryParam("q", artist)
                .queryParam(title)
                .queryParam("type", "track")
                .queryParam("limit", 1)
                .queryParam("duration_ms");


        RestTemplate template = new RestTemplate();
        ResponseEntity<Items[]> items = template.getForEntity(builder.toUriString(), Items[].class);

        System.out.println(items);

//        try{
//            URL url = new URL(query);
//            URLConnection con = url.openConnection();
//            InputStream in = con.getInputStream();
//            String encoding = con.getContentEncoding();
//            encoding = encoding == null ? "UTF-8" : encoding;
//
//            template = new RestTemplate()
//            return IOUtils.toString(in, encoding);
//        }catch (IOException e){
//            e.printStackTrace();
//            return "Empty body";
//        }



        return "";
    }

    private String replaceSpaceChars(String in){
        return in.replace(" ", SPACE);
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
