package pl.tymoteuszborkowski.youtube;

import com.google.api.services.youtube.model.Video;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;

import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

public class VideoFilters {

    private static final String HD_DEFINITION = "hd";


    public List<Video> sortByQuality(List<Video> videos) {
        List<Video> hdVideos = new ArrayList<>();
        List<Video> normalVideos = new ArrayList<>();

        System.out.println("LENGTH BEFORE: " + videos.size());

        for(Video video : videos){
            String definition = video.getContentDetails().getDefinition();
            if(definition.equals(HD_DEFINITION)){
                hdVideos.add(video);
            }else
                normalVideos.add(video);
        }

        System.out.println("LENGTH AFTER: " + videos.size());

        hdVideos.addAll(normalVideos);

        System.out.println("AGAIN : " + hdVideos.size());

        return hdVideos;
    }


    public Map<Long, Video> sortByLength(List<Video> videos) {
        Map<Long, Video> unsortedMap = new HashMap<>();
        Map<Long, Video> sortedMap = new LinkedHashMap<>();

        for (Video video : videos) {
            String duration = video.getContentDetails().getDuration();
            Period period = ISOPeriodFormat.standard().parsePeriod(duration);
            long seconds = period.getSeconds() + (period.getMinutes() * 60);

            unsortedMap.put(seconds, video);
        }

        SortedSet<Long> keys = new TreeSet<>(unsortedMap.keySet());
        for (Long key : keys) {
            Video video = unsortedMap.get(key);
            sortedMap.put(key, video);
        }

        return sortedMap;
    }


    public Video getTheBestVideo(Map<Long, Video> mapWithDurations, long originalTrackDuration) {
        final Set<Long> durationsSet = mapWithDurations.keySet();
        final List<Long> durations = new ArrayList<>(durationsSet);

        long bestKey = 0;
        long distance = Math.abs(durations.get(0) - originalTrackDuration);
        for (int i = 1; i < durations.size(); i++) {
            long cdistance = Math.abs(durations.get(i) - originalTrackDuration);
            if (cdistance < distance) {
                bestKey = durations.get(i);
            }
        }

        return mapWithDurations.get(bestKey);
    }



}
