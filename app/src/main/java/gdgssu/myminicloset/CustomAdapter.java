package gdgssu.myminicloset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Wonkyun Lim on 2015-01-05.
 *
 */
public class CustomAdapter extends BaseAdapter{

    private ArrayList<String> list;
    private LayoutInflater mlayoutInflater;

    public CustomAdapter(ArrayList<String> list, Context context){
        this.list = list;
        mlayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tv = null;

        convertView = mlayoutInflater.inflate(R.layout.list_item_forecast, parent, false);
        tv = (TextView)convertView.findViewById(R.id.list_item_forecast_textview);
        tv.setText(list.get(position));

        return convertView;
    }

}
