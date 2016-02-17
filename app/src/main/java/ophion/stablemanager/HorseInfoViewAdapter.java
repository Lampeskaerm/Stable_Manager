package ophion.stablemanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ophion.stablemanager.objects.Horse;

/**
 * Created by AK on 2/17/2016.
 */
public class HorseInfoViewAdapter extends BaseAdapter {
    private Context mContext;
    private Horse horses[];
    private int count = 10;

    public HorseInfoViewAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return count;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public void setHorseList(Horse[] l) {
        horses = l;
        count = l.length;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.horse_info_view, null);
            convertView = adjustLayout(convertView, position);
            /*// if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);*/
        } else {
            //imageView = (ImageView) convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);
        return convertView;
    }

    private View adjustLayout (View v, int p) {
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout horseitem = (RelativeLayout) li.inflate(R.layout.horse_info_view, null);
        for(int i=0;i<horseitem.getChildCount();i++){
            TextView tv = (TextView)horseitem.getChildAt(i);
            String str = (String) tv.getText();
            tv.setText(str + horses[p].getName());
        }
        return horseitem;
    }
}
