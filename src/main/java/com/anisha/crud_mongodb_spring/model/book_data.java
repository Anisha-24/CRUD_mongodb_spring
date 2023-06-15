package com.anisha.crud_mongodb_spring.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book_data")  //@Document annotation helps us override the collection name by “data”.
public class book_data {

    @org.springframework.data.annotation.Id
    private String id;

    private String Title;
    private String Description;
    private boolean Published;

    public book_data(){

    }

    public book_data(String title, String description, boolean published){
        Title = title;
        Description = description;
        Published = published;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isPublished() {
        return Published;
    }

    public void setPublished(boolean published) {
        Published = published;
    }

    @Override
    public String toString() {
        return "Model_data{" +
                "Id='" + id + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Published=" + Published +
                '}';
    }
}
