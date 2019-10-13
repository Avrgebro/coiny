package com.arsenic.coiny.Model;

import java.io.Serializable;

public class Discount implements Serializable {

    private String company;
    private String small_description;
    private String description;
    private int image_resource_id;

    public Discount(String company, String small_description, String description, int image_resource_id) {
        this.company = company;
        this.small_description = small_description;
        this.description = description;
        this.image_resource_id = image_resource_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSmall_description() {
        return small_description;
    }

    public void setSmall_description(String small_description) {
        this.small_description = small_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage_resource_id() {
        return image_resource_id;
    }

    public void setImage_resource_id(int image_resource_id) {
        this.image_resource_id = image_resource_id;
    }
}
