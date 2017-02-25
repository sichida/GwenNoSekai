package fr.ichida.cms.feature;

import fr.ichida.cms.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by shoun on 25/02/2017.
 */
@RestController
@RequestMapping("/api/v1/tags")
public class TagRestService {
    private final TagService tagService;

    @Autowired
    public TagRestService(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = {"", "/"}, method = GET)
    public ResponseEntity<List<String>> query(@RequestParam("q") String query) {
        return ResponseEntity.ok(tagService.findTags(query));
    }
}
