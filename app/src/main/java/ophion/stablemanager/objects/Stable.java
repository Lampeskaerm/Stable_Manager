package ophion.stablemanager.objects;

import java.util.ArrayList;

/**
 * Created by AK on 2/16/2016.
 */
public class Stable {
    private int id;
    private String name;
    private ArrayList<User> stabledUsers;
    private ArrayList<User> stableOwners;
    private ArrayList<Horse> stabledHorses;
    private ArrayList<User> otherUsers;
    private ArrayList<Box> stableBoxes;
    private int totalBoxes;
    private int availableBoxes;
    private int occupiedBoxes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getStabledUsers() {
        return stabledUsers;
    }

    public void setStabledUsers(ArrayList<User> stabledUsers) {
        this.stabledUsers = stabledUsers;
    }

    public ArrayList<User> getStableOwners() {
        return stableOwners;
    }

    public void setStableOwners(ArrayList<User> stableOwners) {
        this.stableOwners = stableOwners;
    }

    public ArrayList<Horse> getStabledHorses() {
        return stabledHorses;
    }

    public void setStabledHorses(ArrayList<Horse> stabledHorses) {
        this.stabledHorses = stabledHorses;
    }

    public ArrayList<User> getOtherUsers() {
        return otherUsers;
    }

    public void setOtherUsers(ArrayList<User> otherUsers) {
        this.otherUsers = otherUsers;
    }

    public ArrayList<Box> getStableBoxes() {
        return stableBoxes;
    }

    public void setStableBoxes(ArrayList<Box> stableBoxes) {
        this.stableBoxes = stableBoxes;
    }

    public int getTotalBoxes() {
        return totalBoxes;
    }

    public void setTotalBoxes(int totalBoxes) {
        this.totalBoxes = totalBoxes;
    }

    public int getAvailableBoxes() {
        return availableBoxes;
    }

    public void setAvailableBoxes(int availableBoxes) {
        this.availableBoxes = availableBoxes;
    }

    public int getOccupiedBoxes() {
        return occupiedBoxes;
    }

    public void setOccupiedBoxes(int occupiedBoxes) {
        this.occupiedBoxes = occupiedBoxes;
    }
}
