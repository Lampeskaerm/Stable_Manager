package ophion.stablemanager.objects;

import java.util.ArrayList;

/**
 * Created by AK on 2/16/2016.
 */
public class Horse {
    private int id;
    private int stableId;
    private int boxId;
    private int ownerId;
    private int age;
    private String race = "";
    private String name = "";
    private User owner;
    private String veterinarian = "";
    private String farrier = ""; //Smed
    private String paddockInfo = "";
    private String note = "";
    private ArrayList<User> riders = new ArrayList<>();

    public Horse (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStableId() {
        return stableId;
    }

    public void setStableId(int stable_id) {
        this.stableId = stable_id;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int box_id) {
        this.boxId = box_id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int owner_id) {
        this.ownerId = owner_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public String getFarrier() {
        return farrier;
    }

    public void setFarrier(String farrier) {
        this.farrier = farrier;
    }

    public String getPaddockInfo() {
        return paddockInfo;
    }

    public void setPaddockInfo(String paddockInfo) {
        this.paddockInfo = paddockInfo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<User> getRiders() {
        return riders;
    }

    public void setRiders(ArrayList<User> riders) {
        this.riders = riders;
    }
}
