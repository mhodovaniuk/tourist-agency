package ua.entities;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:53 PM
 */
public class Hotel implements Serializable{
    private int stars,id;
    private String country, city, name;

    public Hotel(int stars, String country, String city, String name) {
        this.stars = stars;
        this.country = country;
        this.city = city;
        this.name = name;
    }

    public Hotel(int stars, int id, String country, String city, String name) {

        this.stars = stars;
        this.id = id;
        this.country = country;
        this.city = city;
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
