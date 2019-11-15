package com.example.user.sahaabcv11;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter  extends BaseAdapter{
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    private Integer[] mThumbIds = {
            R.drawable.menu_0, R.drawable.menu_a,
            R.drawable.menu_u, R.drawable.menu_i,
            R.drawable.menu_ei, R.drawable.menu_o,
            R.drawable.menu_eo, R.drawable.menu_e,
            R.drawable.menu_y, R.drawable.menu_s,
            R.drawable.menu_m, R.drawable.menu_r,
            R.drawable.menu_l, R.drawable.menu_b,
            R.drawable.menu_t, R.drawable.menu_k,
            R.drawable.menu_x, R.drawable.menu_n,
            R.drawable.menu_j, R.drawable.menu_p,
            R.drawable.menu_g, R.drawable.menu_ch,
            R.drawable.menu_gh, R.drawable.menu_h,
            R.drawable.menu_eia, R.drawable.menu_uo,
            R.drawable.menu_ie, R.drawable.menu_yo,
            R.drawable.menu_d, R.drawable.menu_dj,
            R.drawable.menu_gn, R.drawable.menu_nj,
            R.drawable.menu_ja, R.drawable.menu_ju,
            R.drawable.menu_je, R.drawable.menu_jo,
            R.drawable.menu_sh, R.drawable.menu_v,
            R.drawable.menu_z, R.drawable.menu_zh,
            R.drawable.menu_f, R.drawable.menu_ts,
            R.drawable.menu_sth, R.drawable.menu_znak,
            R.drawable.menu_znak2
    };

}
