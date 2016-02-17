package ophion.stablemanager.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AK on 2/16/2016.
 */
public class User {

    public enum PermissionLevel{
        STABLEOWNER,RIDER,HORSEOWNER,EMPLOYEE
    }

    private int id;
    private int age;
    private String name;
    private Date birthday;
    private String email;
    private String password;
    private String phonenumber;
    private ArrayList<PermissionLevel> permissions;
    private ArrayList<Horse> ownedHorses;
    private ArrayList<Horse> riddenHorses;
    private String facebookID;

    public User (int id) {
        this.id = id;
        permissions = new ArrayList<PermissionLevel>();
        ownedHorses = new ArrayList<Horse>();
        riddenHorses = new ArrayList<Horse>();
    }

    public void addOwnedHorse(Horse h) {
        ownedHorses.add(h);
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public int getID() {
        return id;
    }

    public void setAgeFromBirthday(String birthday){

    }

    public int getAge() {
        return age;
    }

    public void setName(String firstName, String lastName) {
        name = firstName + " " + lastName;
    }

    public void setName(String firstName) {
        name = firstName;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public ArrayList<PermissionLevel> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<PermissionLevel> permissions) {
        this.permissions = permissions;
    }

    public ArrayList<Horse> getOwnedHorses() {
        return ownedHorses;
    }

    public void setOwnedHorses(ArrayList<Horse> ownedHorses) {
        this.ownedHorses = ownedHorses;
    }

    public ArrayList<Horse> getRiddenHorses() {
        return riddenHorses;
    }

    public void setRiddenHorses(ArrayList<Horse> riddenHorses) {
        this.riddenHorses = riddenHorses;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }
}
