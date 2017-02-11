package fr.ichida.cms.service;

import fr.ichida.cms.domain.Picture;
import fr.ichida.cms.mongo.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static fr.ichida.cms.feature.PictureRestService.BASE_IMAGE_LOCATION;
import static org.springframework.util.StringUtils.*;

/**
 * Created by shoun on 06/02/2017.
 */
@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Transactional
    public Picture upload(String name, byte[] pictureData) {
        Picture picture = new Picture();
        picture.setData(pictureData);
        picture.setFilename(name);
        picture.setLocation(computeLocation(name));
        return pictureRepository.save(picture);
    }

    private String computeLocation(String originalFilename) {
        StringBuilder result = new StringBuilder(BASE_IMAGE_LOCATION);
        String filename = trimAllWhitespace(originalFilename).replaceAll(" ", "-");
        String name = stripFilenameExtension(filename);
        String extension = getFilenameExtension(filename);
        return result.append(name).append("-")
                .append(LocalDateTime.now().format(this.dateTimeFormatter))
                .append(".").append(extension).toString();
    }

    @Transactional(readOnly = true)
    public byte[] getContent(String id) {
        Picture picture = pictureRepository.findOne(id);
        if (null != picture) {
            return picture.getData();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Picture findById(String pictureId) {
        return pictureRepository.findOne(pictureId);
    }

    @Transactional(readOnly = true)
    public Picture findByLocation(String location) {
        return pictureRepository.findByLocation(location);
    }
}
