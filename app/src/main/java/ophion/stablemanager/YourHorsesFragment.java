package ophion.stablemanager;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import ophion.stablemanager.objects.Horse;
import ophion.stablemanager.objects.User;

/**
 * Created by AK on 2/18/2016.
 */
public class YourHorsesFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private User user;
    private ArrayList<Horse> horses;
    private ArrayList<Integer> removedItems;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        //Set the rootView and inflate it
        View rootView = inflater.inflate(R.layout.fragment_your_horses_test, container,false);

        //Initialize variables
        user = MainActivityFragment.user;
        horses = new ArrayList<Horse>(user.getOwnedHorses().values());

        //Define and set the recyclerview
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        removedItems = new ArrayList<>();

        adapter = new YourHorsesAdapter(horses);
        recyclerView.setAdapter(adapter);

        //Set FAB onClick method
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                AddHorseDialog dialog = new AddHorseDialog();
                FragmentTransaction transaction = fm.beginTransaction();
                //Set the transition animation
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                //Make it fullscreen, use 'content' root view as container
                transaction.add(android.R.id.content,dialog)
                        .addToBackStack(null).commit();
            }
        });

        return rootView;
    }
}
