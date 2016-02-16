package ophion.stablemanager.objects;

import java.util.ArrayList;

/**
 * Created by AK on 2/16/2016.
 */
public class Horse {
    private int id;
    private int stable_id;
    private int box_id;
    private int age;
    private String name;
    private String owner;
    private String veterinarian;
    private String farrier; //Smed
    private String paddockInfo;
    private String note;
    private ArrayList<User> riders;

    public Horse (int id) {
        this.id = id;
    }

    public int GetId() {
        return id;
    }

    public void SetId(int id) {
        this.id = id;
    }

    public int GetStable_id() {
        return stable_id;
    }

    public void SetStable_id(int stable_id) {
        this.stable_id = stable_id;
    }

    public int GetBox_id() {
        return box_id;
    }

    public void SetBox_id(int box_id) {
        this.box_id = box_id;
    }

    public int GetAge() {
        return age;
    }

    public void SetAge(int age) {
        this.age = age;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetOwner() {
        return owner;
    }

    public void SetOwner(String owner) {
        this.owner = owner;
    }

    public String GetVeterinarian() {
        return veterinarian;
    }

    public void SetVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public String GetFarrier() {
        return farrier;
    }

    public void SetFarrier(String farrier) {
        this.farrier = farrier;
    }

    public String GetPaddockInfo() {
        return paddockInfo;
    }

    public void SetPaddockInfo(String paddockInfo) {
        this.paddockInfo = paddockInfo;
    }

    public String GetNote() {
        return note;
    }

    public void SetNote(String note) {
        this.note = note;
    }

    public ArrayList<User> GetRiders() {
        return riders;
    }

    public void SetRiders(ArrayList<User> riders) {
        this.riders = riders;
    }
}
