package fr.ichida.cms.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Blog.
 */

@Document(collection = "blog")
public class Blog implements Serializable {

    @Id
    private String id;

    @NotNull
    @Field("blog_id")
    private String blogId;

    @NotNull
    @Field("blog_title")
    private String blogTitle;

    @Field("blog_seo_description")
    private String blogSeoDescription;

    @Field("configuration")
    private Configuration configuration;

    @Field("description")
    private BlogDescription description;

    @Field("themeId")
    private String themeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogSeoDescription() {
        return blogSeoDescription;
    }

    public void setBlogSeoDescription(String blogSeoDescription) {
        this.blogSeoDescription = blogSeoDescription;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public BlogDescription getDescription() {
        return description;
    }

    public void setDescription(BlogDescription description) {
        this.description = description;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Blog blog = (Blog) o;
        if(blog.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, blog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Blog{" +
            "id=" + id +
            ", blogId='" + blogId + "'" +
            ", blogTitle='" + blogTitle + "'" +
            ", blogSeoDescription='" + blogSeoDescription + "'" +
            ", configuration='" + configuration + "'" +
            ", themeId='" + themeId + "'" +
            ", description='" + description + "'" +
            '}';
    }
}
