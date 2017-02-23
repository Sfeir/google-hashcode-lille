package com.sfeir.hashcode;

import com.sfeir.hashcode.model.Cache;
import com.sfeir.hashcode.model.Endpoint;
import com.sfeir.hashcode.model.Video;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws URISyntaxException, IOException {
        // READ input
        String inputName = args[0];
        Path inputPath = Paths.get(App.class.getResource("/" + inputName).toURI());
        List<String> lines = Files.readAllLines(inputPath, StandardCharsets.UTF_8);
        Init init = new Init(lines.remove(0));
        VideoFactory videoFactory = new VideoFactory(lines.remove(0));
        List<Video> videos = videoFactory.getVideos();
        for (Video v: videos) {
            Video.getVideos().put(v.getId(), v);
        }
        for (int i = 0; i < init.numberOfCaches(); i++) {
            Cache.getCaches().put(i, new Cache(i, init.cacheSize()));
        }
        EndpointFactory endpointFactory = new EndpointFactory(lines,init.numberOfEndpoints());
        List<Endpoint> endpoints = endpointFactory.createEndpoints();
        for (Endpoint e: endpoints) {
            Endpoint.getEndpoints().put(e.getId(), e);
        }
        for (String line : endpointFactory.getRemainsLines()) {
            Endpoint.getEndpoints().get(Integer.valueOf(line.split(" ")[1])).addRequest(Integer.valueOf(line.split(" ")[0]),Integer.valueOf(line.split(" ")[2]));
        }
        // Write output

        doYourJob();

        List<String > res = new ArrayList<>();
        for (Cache c: Cache.getCaches().values()) {
            res.add(c.getOutput());
        }
        Files.write(new File(args[1]).toPath(), res);
    }

    private static void doYourJob() {

    }
}
