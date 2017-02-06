package fr.ichida.cms.feature;

import fr.ichida.cms.domain.Picture;
import fr.ichida.cms.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by shoun on 06/02/2017.
 */
@RestController
@RequestMapping("/api/v1/picture")
public class PictureRestService {

    private final PictureService pictureService;

    @Autowired
    public PictureRestService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public ResponseEntity<Picture> uploadThumbnail(MultipartFile file) throws IOException {
        return ResponseEntity.ok(pictureService.upload(file.getBytes()));
    }
}
