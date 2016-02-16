package ophion.stablemanager.objects;

import java.util.ArrayList;

/**
 * Created by AK on 2/16/2016.
 */
public class Stable {
    private int id;
    private String name;
    private ArrayList<User> StabledUsers;
    private ArrayList<User> StableOwners;
    private ArrayList<Horse> StabledHorses;
    private ArrayList<User> OtherUsers;
    private ArrayList<Box> StableBoxes;
    private int totalBoxes;
    private int availableBoxes;
    private int occupiedBoxes;

    public int GetId() {
        return id;
    }

    public void SetId(int id) {
        this.id = id;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public ArrayList<User> GetStabledUsers() {
        return StabledUsers;
    }

    public void SetStabledUsers(ArrayList<User> stabledUsers) {
        StabledUsers = stabledUsers;
    }

    public ArrayList<User> GetStableOwners() {
        return StableOwners;
    }

    public void SetStableOwners(ArrayList<User> owners) {
        StableOwners = owners;
    }

    public ArrayList<Horse> GetStabledHorses() {
        return StabledHorses;
    }

    public void SetStabledHorses(ArrayList<Horse> stabledHorses) {
        StabledHorses = stabledHorses;
    }

    public ArrayList<User> GetOtherUsers() {
        return OtherUsers;
    }

    public void SetOtherUsers(ArrayList<User> otherUsers) {
        OtherUsers = otherUsers;
    }

    public ArrayList<Box> GetStableBoxes() {
        return StableBoxes;
    }

    public void SetStableBoxes(ArrayList<Box> stableBoxes) {
        StableBoxes = stableBoxes;
    }

    public int GetTotalBoxes() {
        return totalBoxes;
    }

    public void SetTotalBoxes(int totalBoxes) {
        this.totalBoxes = totalBoxes;
    }

    public int GetAvailableBoxes() {
        return availableBoxes;
    }

    public void SetAvailableBoxes(int availableBoxes) {
        this.availableBoxes = availableBoxes;
    }

    public int GetOccupiedBoxes() {
        return occupiedBoxes;
    }

    public void SetOccupiedBoxes(int occupiedBoxes) {
        this.occupiedBoxes = occupiedBoxes;
    }
}
