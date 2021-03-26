package co.uk.bawmpt.unimyportalapp.model;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class Book implements Serializable {
    private String Image;
    private String Title;
    private String Author;
    private String Edition;
    private String ISBN;
    private String Category;
    private String description;
    private String imageUrl;
    private Long Available;
    private Timestamp timeAdded;
    private String userName;
    private String userId;


    public Book(){
    }

    public Book (String image, String title, String author, String edition, String isbn,
                String category, String description, String imageUrl, Long available,
                Timestamp timeAdded, String userName, String userId) {
        this.Image = image;
        this.Title = title;
        this.Author = author;
        this.Edition = edition;
        this.ISBN = isbn;
        this.Category = category;
        this.description = description;
        this.imageUrl = imageUrl;
        this.Available = available;
        this.timeAdded = timeAdded;
        this.userName = userName;
        this.userId = userId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public String getEdition() {
        return Edition;
    }

    public void setEdition(String edition) {
        this.Edition = edition;
    }

    public String getIsbn() {
        return ISBN;
    }

    public void setIsbn(String isbn) {
        this.ISBN = isbn;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAvailable() {
        return Available;
    }
    public void setAvailable(Long available) {
        this.Available = available;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
