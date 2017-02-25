package fr.ichida.cms.service;

import fr.ichida.cms.domain.TagProjection;
import fr.ichida.cms.mongo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shoun on 25/02/2017.
 */
@Service
public class TagService {

    private final ArticleRepository articleRepository;

    @Autowired
    public TagService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<String> findTags(String query) {
        return articleRepository.findDistinctTags(query).stream()
                .map(TagProjection::getTags)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
