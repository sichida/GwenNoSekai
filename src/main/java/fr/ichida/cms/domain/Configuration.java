package fr.ichida.cms.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

/**
 * <p>
 *
 * @author shoun
 * @since 12/03/2016
 */
public class Configuration {
    @Field("item_per_page")
    private Integer itemPerPage;

    @Field("display_author")
    private Boolean displayAuthor;

    @Field("pinterestAccount")
    private String pinterestAccount;

    @Field("facebook_page")
    private String facebookPage;

    @Field("instagram_account")
    private String instagramAccount;

    @Field("twitter_account")
    private String twitterAccount;

    public Integer getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(Integer itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public Boolean getDisplayAuthor() {
        return displayAuthor;
    }

    public void setDisplayAuthor(Boolean displayAuthor) {
        this.displayAuthor = displayAuthor;
    }

    public String getPinterestAccount() {
        return pinterestAccount;
    }

    public void setPinterestAccount(String pinterestAccount) {
        this.pinterestAccount = pinterestAccount;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getInstagramAccount() {
        return instagramAccount;
    }

    public void setInstagramAccount(String instagramAccount) {
        this.instagramAccount = instagramAccount;
    }

    public String getTwitterAccount() {
        return twitterAccount;
    }

    public void setTwitterAccount(String twitterAccount) {
        this.twitterAccount = twitterAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(itemPerPage, that.itemPerPage) &&
            Objects.equals(displayAuthor, that.displayAuthor) &&
            Objects.equals(pinterestAccount, that.pinterestAccount) &&
            Objects.equals(facebookPage, that.facebookPage) &&
            Objects.equals(instagramAccount, that.instagramAccount) &&
            Objects.equals(twitterAccount, that.twitterAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemPerPage, displayAuthor, pinterestAccount, facebookPage, instagramAccount, twitterAccount);
    }

    @Override
    public String toString() {
        return "Configuration{" +
            "itemPerPage='" + itemPerPage + "'" +
            ", displayAuthor='" + displayAuthor + "'" +
            ", pinterestAccount='" + pinterestAccount + "'" +
            ", facebookPage='" + facebookPage + "'" +
            ", instagramAccount='" + instagramAccount + "'" +
            ", twitterAccount='" + twitterAccount + "'" +
            '}';
    }
}
