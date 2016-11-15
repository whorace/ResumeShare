package edu.brandeis.cs.jiahuiming.resumeshare.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by jiahuiming on 10/25/16.
 */
public class ExperienceAdapter extends BaseAdapter {
    private Context context;
    private String account;

    public ExperienceAdapter(Context context,String account) {
        this.account = account;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
