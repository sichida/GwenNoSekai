package fr.ichida.cms.service;

import fr.ichida.cms.domain.Blog;
import fr.ichida.cms.repository.BlogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Blog.
 */
@Service
public class BlogService {

    private final Logger log = LoggerFactory.getLogger(BlogService.class);
    
    @Inject
    private BlogRepository blogRepository;
    
    /**
     * Save a blog.
     * @return the persisted entity
     */
    public Blog save(Blog blog) {
        log.debug("Request to save Blog : {}", blog);
        Blog result = blogRepository.save(blog);
        return result;
    }

    /**
     *  get all the blogs.
     *  @return the list of entities
     */
    public List<Blog> findAll() {
        log.debug("Request to get all Blogs");
        List<Blog> result = blogRepository.findAll();
        return result;
    }

    /**
     *  get one blog by id.
     *  @return the entity
     */
    public Blog findOne(String id) {
        log.debug("Request to get Blog : {}", id);
        Blog blog = blogRepository.findOne(id);
        return blog;
    }

    /**
     *  delete the  blog by id.
     */
    public void delete(String id) {
        log.debug("Request to delete Blog : {}", id);
        blogRepository.delete(id);
    }
}
