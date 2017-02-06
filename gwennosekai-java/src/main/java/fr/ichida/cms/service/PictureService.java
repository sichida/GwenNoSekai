package fr.ichida.cms.service;

import fr.ichida.cms.domain.Picture;
import fr.ichida.cms.mongo.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shoun on 06/02/2017.
 */
@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture upload(byte[] pictureData) {
        Picture picture = new Picture();
        picture.setData(pictureData);
        return pictureRepository.save(picture);
    }
}
