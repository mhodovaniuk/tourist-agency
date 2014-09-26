package ua.database.dao;

import ua.entities.History;
import ua.entities.User;

import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 * User: Mychajlo Godovanjuk
 * Date: 6/10/13
 * Time: 12:45 PM
 */
public interface HistoryDAO {
    ArrayList<History> getHistoryByUser(User user);
    boolean addHistory(History history);
    int getTotalSpentByUser(User user);
}
