package ua.database.dao;

import ua.entities.Hotel;

import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:44 PM
 */
public interface HotelDAO {
    boolean addHotel(Hotel hotel);
    Hotel getHotel(int id);
    ArrayList getAllHotels();
    boolean removeHotel(int id);
}
