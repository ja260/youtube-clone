package com.example.youtubeclone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class Homecontroller {

    record Video(String title, String channel, String views, String embedUrl, String watchUrl) {}

 private final List<Video> videos = List.of(
        new Video(
                "My Video",
                "Channel",
                "--",
                toEmbedUrl("https://youtu.be/vB0V3iCSzQw"),
                "https://www.youtube.com/watch?v=vB0V3iCSzQw"
        ),
        new Video(
                "Chori Chori Dil Tera (Cover)",
                "Hindi Unplugged World",
                "811K views",
                toEmbedUrl("https://www.youtube.com/watch?v=55BS8QO5C9o"),
                "https://www.youtube.com/watch?v=55BS8QO5C9o"
        ),
        new Video(
                "Sample Video 2",
                "Channel 2",
                "4.8M views",
                toEmbedUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ"),
                "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        ),
        new Video(
                "Sample Video 3",
                "Channel 3",
                "17M views",
                toEmbedUrl("https://www.youtube.com/watch?v=kJQP7kiw5Fk"),
                "https://www.youtube.com/watch?v=kJQP7kiw5Fk"
        )
);
private String toEmbedUrl(String url) {
    if (url == null) return "";
    url = url.trim();

    // already embed
    if (url.contains("/embed/")) return url;

    // youtu.be/<id>
    if (url.contains("youtu.be/")) {
        String id = url.substring(url.indexOf("youtu.be/") + 9);
        id = id.split("[?&#/ ]")[0];
        return "https://www.youtube.com/embed/" + id;
    }

    // youtube.com/watch?v=<id>
    int vi = url.indexOf("v=");
    if (vi != -1) {
        String id = url.substring(vi + 2);
        id = id.split("[?&#/ ]")[0];
        return "https://www.youtube.com/embed/" + id;
    }

    return url; // fallback
}

    @GetMapping("/")
    public String home(@RequestParam(name = "v", required = false) Integer v, Model model) {
        int idx = (v == null || v < 0 || v >= videos.size()) ? 0 : v;
        model.addAttribute("current", videos.get(idx));
        model.addAttribute("videos", videos);
        return "index";
    }
}
