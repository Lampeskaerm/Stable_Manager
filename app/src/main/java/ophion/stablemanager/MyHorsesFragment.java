package ophion.stablemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import ophion.stablemanager.objects.Horse;
import ophion.stablemanager.objects.User;

/**
 * Created by AK on 2/15/2016.
 */
public class MyHorsesFragment extends Fragment {
    User user;

    public MyHorsesFragment() {

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.my_horses_fragment, container,false);

        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        HorseInfoViewAdapter va = new HorseInfoViewAdapter(getContext());
        User user = MainActivityFragment.user;
        va.setHorseList((Horse[]) user.getOwnedHorses()
                .toArray(new Horse[user.getOwnedHorses().size()]));
        gridview.setAdapter(va);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Log.d("GridView", "" + position);
            }
        });

        return rootView;
    }
}
