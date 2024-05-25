package com.aims.service;

import com.aims.entity.Media.Media;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MediaService {
    public List<Media> findAllMedia();

    public Optional<Media> findMediaById(ObjectId id);
}
