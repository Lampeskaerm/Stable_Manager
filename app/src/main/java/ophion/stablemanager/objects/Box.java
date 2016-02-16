package ophion.stablemanager.objects;

/**
 * Created by AK on 2/16/2016.
 */
public class Box {
    private int id;
    private Horse horse;

    public int GetId() {
        return id;
    }

    public void SetId(int id) {
        this.id = id;
    }

    public Horse GetHorse() {
        return horse;
    }

    public void SetHorse(Horse horse) {
        this.horse = horse;
    }
}
