package ua.database.dao;

import ua.entities.Tour;

import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:44 PM
 */
public interface TourDAO {
    boolean addTour(Tour tour);
    ArrayList<Tour> getAllTours();
    Tour getTour(int id);
    boolean removeTour(int id);
}
