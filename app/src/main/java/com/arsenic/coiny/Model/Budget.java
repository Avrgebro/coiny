package com.arsenic.coiny.Model;

import java.io.Serializable;

/*
 * Created by: jose
 * Compamny: Dom Peru
 * Date: 10/15/19
 */
public class Budget implements Serializable {

    private String type;
    private double budget;
    private double used;
    private int resource_id;

    public Budget(String type, double budget, double used, int resource_id) {
        this.type = type;
        this.budget = budget;
        this.used = used;
        this.resource_id = resource_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }
}
