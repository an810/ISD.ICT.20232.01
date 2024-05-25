package com.aims.controller;

import java.util.List;
import java.util.Optional;

import com.aims.entity.Media.Media;
import com.aims.service.MediaService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/home")
public class HomeController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/all")
    public ResponseEntity<List<Media>> getMovies() {
        List<Media> mediaList = mediaService.findAllMedia();
        if (mediaList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(mediaList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> findMediaById(@PathVariable String id) {
        Optional<Media> media = mediaService.findMediaById(new ObjectId(id));
        return media.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}