package ua.entities;

import org.apache.log4j.Logger;
import ua.database.dao.postgre.PostgreUserDAO;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 1:10 PM
 */
public class History implements Serializable {
    private Tour tour;
    private User user;
    private int id, adultCount, childrenCount,totalCost;
    private static double HOT_DISCOUNT;
    private static final Logger logger= Logger.getLogger(History.class);
    protected static Properties properties=new Properties();
    static {
        try {
            properties.load(new FileReader("/home/mychajlo/server.properties"));
            HOT_DISCOUNT=Double.parseDouble(properties.getProperty("hot_discount"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal("Can't load properties file");
        }
    }
    public History(Tour tour, User user, int adultCount, int childrenCount) {
        this.tour = tour;
        this.user = user;
        this.adultCount = adultCount;
        this.childrenCount = childrenCount;
        this.totalCost = produceTotalCost();
        id=-1;
    }

    public History(Tour tour, User user, int id, int adultCount, int childrenCount,int totalCost) {
        this.tour = tour;
        this.user = user;
        this.id = id;
        this.adultCount = adultCount;
        this.childrenCount = childrenCount;
        this.totalCost = totalCost;
    }

    private int produceTotalCost(){
        double userDiscount=user.isRegularCustomer()?tour.getDiscountForRegularCustomer():0;
        return (int)((100-HOT_DISCOUNT-userDiscount)*(adultCount*tour.getAdultCost()+childrenCount*tour.getChildrenCost())/100);
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getStrTotalCost(){
        return "$"+totalCost/100+"."+totalCost%100;
    }

    public String getUID(){
        return String.valueOf(System.currentTimeMillis());
    }
}
