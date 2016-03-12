package fr.ichida.cms.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * A Article.
 */

@Document(collection = "article")
public class Article implements Serializable {

    @Id
    private String id;

    @NotNull
    @Field("title")
    private String title;

    @Field("content")
    private String content;

    @NotNull
    @Field("permalink")
    private String permalink;

    @NotNull
    @Field("creation_date")
    private ZonedDateTime creationDate;

    @Field("last_update_date")
    private ZonedDateTime lastUpdateDate;

    @NotNull
    @Field("is_published")
    private Boolean isPublished;

    @Field("enable_public_preview")
    private Boolean enablePublicPreview;

    @NotNull
    @Field("author")
    private String author;

    @Field("thumbnail")
    private Long thumbnail;

    @Field("tags")
    private Set<String> tags;

    @Field("categories")
    private Set<String> categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(ZonedDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Boolean getEnablePublicPreview() {
        return enablePublicPreview;
    }

    public void setEnablePublicPreview(Boolean enablePublicPreview) {
        this.enablePublicPreview = enablePublicPreview;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Long thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        if(article.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", content='" + content + "'" +
            ", permalink='" + permalink + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastUpdateDate='" + lastUpdateDate + "'" +
            ", isPublished='" + isPublished + "'" +
            ", enablePublicPreview='" + enablePublicPreview + "'" +
            ", author='" + author + "'" +
            ", thumbnail='" + thumbnail + "'" +
            ", tags='" + tags + "'" +
            ", categories='" + categories + "'" +
            '}';
    }
}
