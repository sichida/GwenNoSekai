package fr.ichida.cms.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

/**
 * <p>
 *
 * @author shoun
 * @since 12/03/2016
 */
public class BlogDescription {
    @Field("item_per_page")
    private String title;

    @Field("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogDescription that = (BlogDescription) o;
        return Objects.equals(title, that.title) &&
            Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BlogDescription{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
