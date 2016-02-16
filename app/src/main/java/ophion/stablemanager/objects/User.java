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

    private int ID;
    private int age;
    private String name;
    private Date birthday;
    private String email;
    private String password;
    private String phonenumber;
    private ArrayList<PermissionLevel> permissions;
    private ArrayList<Horse> owned;
    private ArrayList<Horse> riding;
    private String facebookID;

    public User (int ID) {
        this.ID = ID;
    }

    public void SetID (int ID) {
        this.ID = ID;
    }

    public int GetID () {
        return ID;
    }

    public void SetAgeFromBirthday(String birthday){

    }

    public int GetAge() {
        return age;
    }

    public void SetName (String firstName, String lastName) {
        name = firstName + " " + lastName;
    }

    public void SetName (String firstName) {
        name = firstName;
    }

    public String GetName () {
        return name;
    }

    public Date GetBirthday() {
        return birthday;
    }

    public void SetBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String GetEmail() {
        return email;
    }

    public void SetEmail(String email) {
        this.email = email;
    }

    public String GetPassword() {
        return password;
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    public String GetPhonenumber() {
        return phonenumber;
    }

    public void SetPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public ArrayList<PermissionLevel> GetPermissions() {
        return permissions;
    }

    public void SetPermissions(ArrayList<PermissionLevel> permissions) {
        this.permissions = permissions;
    }

    public ArrayList<Horse> GetOwned() {
        return owned;
    }

    public void SetOwned(ArrayList<Horse> owned) {
        this.owned = owned;
    }

    public ArrayList<Horse> GetRiding() {
        return riding;
    }

    public void SetRiding(ArrayList<Horse> riding) {
        this.riding = riding;
    }

    public String GetFacebookID() {
        return facebookID;
    }

    public void SetFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }
}
