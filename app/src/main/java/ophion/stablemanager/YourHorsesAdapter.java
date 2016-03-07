package ophion.stablemanager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ophion.stablemanager.objects.Horse;

/**
 * Created by AK on 2/18/2016.
 */
public class YourHorsesAdapter extends RecyclerView.Adapter<YourHorsesAdapter.ViewHolder> {

    private ArrayList<Horse> horses;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewAge;
        TextView textViewMore;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.name);
            this.textViewAge = (TextView) itemView.findViewById(R.id.age);
            this.textViewMore = (TextView) itemView.findViewById(R.id.more);
        }
    }

    public YourHorsesAdapter(ArrayList<Horse> horses) {
        this.horses = horses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horse_cards,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("YourHorses","You clicked a horse!");
            }
        });

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewAge = holder.textViewAge;
        TextView textViewMore = holder.textViewMore;

        textViewName.setText("Name: " + horses.get(position).getName());
        textViewAge.setText("Age: " + horses.get(position).getAge());
        textViewMore.setText("See more...");
    }

    @Override
    public int getItemCount() {
        return horses.size();
    }
}
