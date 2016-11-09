package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.brandeis.cs.jiahuiming.resumeshare.R;
import edu.brandeis.cs.jiahuiming.resumeshare.views.widgets.NavigationItem;

/**
 * Created by jiahuiming on 11/6/16.
 */

public class MenuItemAdapter extends BaseAdapter {
    private ArrayList<NavigationItem> data;
    private LayoutInflater inflator;
    private TextView title;
    private ImageView icon;
    private Context context;

    public MenuItemAdapter(Context context, ArrayList<NavigationItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.drawer_list_item, null);
            title =(TextView) convertView.findViewById(R.id.Tv_Title);
            icon = (ImageView) convertView.findViewById(R.id.Iv_Image);
        }

        title.setText(data.get(position).getTitle());
        icon.setBackground(data.get(position).getDrawable());
        return convertView;
    }


}
