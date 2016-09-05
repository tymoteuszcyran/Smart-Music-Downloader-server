package pl.tymoteuszborkowski.youtube;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class Download {

    private static final String MP4_EXTENSION = ".mp4";


    public void downloadVideo(final String url) throws IOException {
        String[] args = new String[] {
                        "/bin/bash", // if windows change to cmd location
                        "-c",
                        "youtube-dl" +
                        " --extract-audio" +
                        " --audio-format mp3" +
                        " --audio-quality 0 " +
                        url};

        ProcessBuilder builder = new ProcessBuilder(args);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }


    private static File createTempFile() throws IOException {
        File file = File.createTempFile(UUID.randomUUID().toString(), MP4_EXTENSION);
        file.deleteOnExit();

        return file;
    }
}
