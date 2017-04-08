package pl.tymoteuszborkowski.downloader;

import com.google.api.services.youtube.model.Video;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MusicDownloader {

    private static final Logger log = Logger.getLogger(MusicDownloader.class);
    private static final String CMD_DESTINATION = "/bin/bash";
    private static final String CMD_RUN = "-c";
    private static final String YOUTUBE_DL_FOLDER = "cd src/main/resources/youtube-dl && ";
    private static final String YOUTUBE_DL_EXEC = "python youtube-dl --extract-audio --audio-format mp3 --audio-quality 0 ";
    private static final String YOUTUBE_URL_PREFIX = "https://www.youtube.com/watch?v=";

    public void downloadMp3(String url) {
        String[] args = new String[]{
                CMD_DESTINATION,
                CMD_RUN,
                YOUTUBE_DL_FOLDER +
                YOUTUBE_DL_EXEC +
                url
                };


        try {
            ProcessBuilder builder = new ProcessBuilder(args);
            builder.redirectErrorStream(true);
            Process p = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                log.info(line);
                if (line == null) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String fileDirectory(Video video) {
        String filename = video.getSnippet().getTitle();
        File folder = new File(System.getProperty("user.dir"));
        File[] files = folder.listFiles();
        String path = "";

        if (files != null) {
            for (File file : files) {
                if (file.getName().contains(filename)) {
                    path = file.getPath();
                    break;
                }
            }
        }

        return path;
    }


    public String getURL(Video video) {
        String videoId = video.getId();
        return YOUTUBE_URL_PREFIX + videoId;
    }

}
