package ophion.stablemanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by AK on 2/15/2016.
 */
public class StableFragment extends Fragment {

    public StableFragment() {

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stable_fragment, container, false);

        return view;
    }
}
