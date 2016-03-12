package fr.ichida.cms.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.ichida.cms.domain.Theme;
import fr.ichida.cms.service.ThemeService;
import fr.ichida.cms.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Theme.
 */
@RestController
@RequestMapping("/api")
public class ThemeResource {

    private final Logger log = LoggerFactory.getLogger(ThemeResource.class);
        
    @Inject
    private ThemeService themeService;
    
    /**
     * POST  /themes -> Create a new theme.
     */
    @RequestMapping(value = "/themes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Theme> createTheme(@Valid @RequestBody Theme theme) throws URISyntaxException {
        log.debug("REST request to save Theme : {}", theme);
        if (theme.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("theme", "idexists", "A new theme cannot already have an ID")).body(null);
        }
        Theme result = themeService.save(theme);
        return ResponseEntity.created(new URI("/api/themes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("theme", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /themes -> Updates an existing theme.
     */
    @RequestMapping(value = "/themes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Theme> updateTheme(@Valid @RequestBody Theme theme) throws URISyntaxException {
        log.debug("REST request to update Theme : {}", theme);
        if (theme.getId() == null) {
            return createTheme(theme);
        }
        Theme result = themeService.save(theme);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("theme", theme.getId().toString()))
            .body(result);
    }

    /**
     * GET  /themes -> get all the themes.
     */
    @RequestMapping(value = "/themes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Theme> getAllThemes() {
        log.debug("REST request to get all Themes");
        return themeService.findAll();
            }

    /**
     * GET  /themes/:id -> get the "id" theme.
     */
    @RequestMapping(value = "/themes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Theme> getTheme(@PathVariable String id) {
        log.debug("REST request to get Theme : {}", id);
        Theme theme = themeService.findOne(id);
        return Optional.ofNullable(theme)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /themes/:id -> delete the "id" theme.
     */
    @RequestMapping(value = "/themes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTheme(@PathVariable String id) {
        log.debug("REST request to delete Theme : {}", id);
        themeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("theme", id.toString())).build();
    }
}
