package com.anisha.crud_mongodb_spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "data")  //@Document annotation helps us override the collection name by “data”.
public class Model_data {

    @org.springframework.data.annotation.Id
    private String Id;

    private String Title;
    private String Description;
    private boolean Published;

    public Model_data(){

    }

    public Model_data(String title, String description, boolean published){
        Title = title;
        Description = description;
        Published = published;
    }

    public String getId() {
        return Id;
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
                "Id='" + Id + '\'' +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Published=" + Published +
                '}';
    }
}
