package com.aims.service.impl;

import com.aims.entity.Media.Media;
import com.aims.repository.MediaRepository;
import com.aims.service.MediaService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    private MediaRepository mediaRepository;
    @Override
    public List<Media> findAllMedia() {
        return mediaRepository.findAll();
    }
    @Override
    public Optional<Media> findMediaById(ObjectId id){
        return mediaRepository.findById(id);
    }
}
