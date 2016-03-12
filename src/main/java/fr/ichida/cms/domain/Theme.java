package fr.ichida.cms.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Theme.
 */

@Document(collection = "theme")
public class Theme implements Serializable {

    @Id
    private String id;

    @NotNull
    @Field("theme_id")
    private String themeId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        Theme theme = (Theme) o;
        if(theme.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, theme.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Theme{" +
            "id=" + id +
            ", themeId='" + themeId + "'" +
            '}';
    }
}
