package com.amciof.Yandex_MDS2019.Activities.ListOfApplications;


import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amciof.Yandex_MDS2019.R;



public class ApplicationHolder
        extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView labelView;

    ApplicationHolder(View view){
        super(view);
        imageView = view.findViewById(R.id.item_app_icon);
        labelView = view.findViewById(R.id.item_app_label);

    }

    public void setData(Drawable image, CharSequence label) {
        imageView.setImageDrawable(image);
        labelView.setText(label);
    }

}
