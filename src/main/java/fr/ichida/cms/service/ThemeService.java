package fr.ichida.cms.service;

import fr.ichida.cms.domain.Theme;
import fr.ichida.cms.repository.ThemeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Theme.
 */
@Service
public class ThemeService {

    private final Logger log = LoggerFactory.getLogger(ThemeService.class);
    
    @Inject
    private ThemeRepository themeRepository;
    
    /**
     * Save a theme.
     * @return the persisted entity
     */
    public Theme save(Theme theme) {
        log.debug("Request to save Theme : {}", theme);
        Theme result = themeRepository.save(theme);
        return result;
    }

    /**
     *  get all the themes.
     *  @return the list of entities
     */
    public List<Theme> findAll() {
        log.debug("Request to get all Themes");
        List<Theme> result = themeRepository.findAll();
        return result;
    }

    /**
     *  get one theme by id.
     *  @return the entity
     */
    public Theme findOne(String id) {
        log.debug("Request to get Theme : {}", id);
        Theme theme = themeRepository.findOne(id);
        return theme;
    }

    /**
     *  delete the  theme by id.
     */
    public void delete(String id) {
        log.debug("Request to delete Theme : {}", id);
        themeRepository.delete(id);
    }
}
