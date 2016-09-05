package pl.tymoteuszborkowski.youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Download {
    
    private static final String CMD_DESTINATION = "/bin/bash";
    private static final String CMD_RUN = "-c";
    private static final String YOUTUBE_DL_EXEC = "youtube-dl";
    private static final String EXTRACT_AUDIO = " --extract-audio";
    private static final String AUDIO_FORMAT = " --audio-format mp3";
    private static final String AUDIO_QUALITY = " --audio-quality 0 ";

    public void downloadMp3(final String url) throws IOException {
        String[] args = new String[]{
                CMD_DESTINATION,
                CMD_RUN,
                YOUTUBE_DL_EXEC +
                EXTRACT_AUDIO +
                AUDIO_FORMAT +
                AUDIO_QUALITY +
                url};

        ProcessBuilder builder = new ProcessBuilder(args);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }
}
