package ophion.stablemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ophion.stablemanager.objects.User;

/**
 * Created by AK on 2/15/2016.
 */
public class UserFragment extends Fragment {
    User user;

    public UserFragment() {

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user, container,false);



        return rootView;
    }
}
