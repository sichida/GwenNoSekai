package fr.ichida.cms.feature;

import fr.ichida.cms.domain.Picture;
import fr.ichida.cms.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by shoun on 06/02/2017.
 */
@RestController
public class PictureRestService {
    public static final String BASE_IMAGE_LOCATION = "/pictures/";
    private static final String API_BASE_URL = "/api/v1/picture";
    private final PictureService pictureService;

    @Autowired
    public PictureRestService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @RequestMapping(value = {API_BASE_URL, API_BASE_URL + "/"}, method = POST)
    public ResponseEntity<Picture> upload(MultipartFile file) throws IOException {
        return ResponseEntity.ok(pictureService.upload(file.getOriginalFilename(), file.getBytes()));
    }

    @RequestMapping(value = API_BASE_URL + "/content/{id}", method = GET)
    public ResponseEntity<byte[]> getContent(@PathVariable("id") String id) {
        byte[] content = pictureService.getContent(id);
        if (null != content) {
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = API_BASE_URL + "/{id}", method = GET)
    public ResponseEntity<Picture> findById(@PathVariable("id") String pictureId) {
        return ResponseEntity.ok(pictureService.findById(pictureId));
    }

    @RequestMapping(value = BASE_IMAGE_LOCATION + "{location:.+}", method = GET)
    public ResponseEntity<byte[]> findByLocation(@PathVariable("location") String location) {
        Picture picture = pictureService.findByLocation(BASE_IMAGE_LOCATION + location);
        if (null != picture) {
            return ResponseEntity.ok(picture.getData());
        }
        return ResponseEntity.notFound().build();
    }
}
