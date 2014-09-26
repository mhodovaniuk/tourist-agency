package ua.entities;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.Date;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:56 PM
 */
public class Tour implements Serializable {
    private Date startDate;
    private Hotel hotel;
    private int id, dayCount, nightCount;
    private int adultCost;
    private int childrenCost;
    private double discountForRegularCustomer;
    private boolean hot;

    public enum TourType{
        SHOPPING("Shopping"), EXCURSION("Excursion"), VACATION("Vacation");
        private String type;

        private TourType(String type){
            this.type=type;
        }

        public String getType(){
            return type;
        }
    }

    public Tour(Date startDate, Hotel hotel, int id, int dayCount, int nightCount, int adultCost
            , int childrenCost, double discountForRegularCustomer, boolean hot) {
        this.startDate = startDate;
        this.hotel = hotel;
        this.id = id;
        this.dayCount = dayCount;
        this.nightCount = nightCount;
        this.adultCost = adultCost;
        this.childrenCost = childrenCost;
        this.discountForRegularCustomer = discountForRegularCustomer;
        this.hot = hot;
    }

    public Tour(Date startDate, Hotel hotel, int dayCount, int nightCount, int adultCost
            , int childrenCost, double discountForRegularCustomer, boolean hot) {
        this.startDate = startDate;
        this.hotel = hotel;
        this.dayCount = dayCount;
        this.nightCount = nightCount;
        this.adultCost = adultCost;
        this.childrenCost = childrenCost;
        this.discountForRegularCustomer = discountForRegularCustomer;
        this.hot = hot;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getNightCount() {
        return nightCount;
    }

    public void setNightCount(int nightCount) {
        this.nightCount = nightCount;
    }

    public int getAdultCost() {
        return adultCost;
    }

    public void setAdultCost(int adultCost) {
        this.adultCost = adultCost;
    }

    public int getChildrenCost() {
        return childrenCost;
    }

    public void setChildrenCost(int childrenCost) {
        this.childrenCost = childrenCost;
    }

    public double getDiscountForRegularCustomer() {
        return discountForRegularCustomer;
    }

    public void setDiscountForRegularCustomer(double discountForRegularCustomer) {
        this.discountForRegularCustomer = discountForRegularCustomer;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public String getStrAdultCost(){
        return "$"+adultCost/100+"."+adultCost%100;
    }
    public String getStrChildrenCost(){
        return "$"+childrenCost/100+"."+childrenCost%100;
    }
    public String getStrDiscountForRegularCustomer(){
        return discountForRegularCustomer+"%";
    }
}