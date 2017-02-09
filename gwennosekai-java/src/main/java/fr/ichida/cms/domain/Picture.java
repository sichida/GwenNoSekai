package fr.ichida.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by shoun on 06/02/2017.
 */
@Document
public class Picture {
    @Id
    private String id;
    @JsonIgnore
    private byte[] data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
