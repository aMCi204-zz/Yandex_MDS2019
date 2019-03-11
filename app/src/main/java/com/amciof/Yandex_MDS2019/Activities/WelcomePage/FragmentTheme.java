package com.amciof.Yandex_MDS2019.Activities.WelcomePage;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.amciof.Yandex_MDS2019.R;
import com.amciof.Yandex_MDS2019.SPHelper;



public class FragmentTheme
        extends Fragment
        implements OnCheckedChangeListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_3, container,false);

        RadioGroup radioGroupTheme = rootView.findViewById(R.id.radio_group_theme);
        radioGroupTheme.setOnCheckedChangeListener(this);

        RadioButton radioButtonLight = rootView.findViewById(R.id.radio_button_light);

        RadioButton radioButtonDark = rootView.findViewById(R.id.radio_button_dark);

        String theme = SPHelper.getString(rootView.getContext(), SPHelper.THEME, SPHelper.LIGHT);
        switch (theme) {
            case SPHelper.LIGHT:
                radioButtonLight.setChecked(true);
                break;
            case SPHelper.DARK:
                radioButtonDark.setChecked(true);
                break;
        }

        Button buttonNext = rootView.findViewById(R.id.button_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityWelcomePage)getActivity()).nextPage();
            }
        });

        return rootView;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_button_light:
                SPHelper.putString(getContext(), SPHelper.THEME, SPHelper.LIGHT);
                getContext().getApplicationContext().setTheme(R.style.AppTheme);
                break;
            case R.id.radio_button_dark:
                SPHelper.putString(getContext(), SPHelper.THEME, SPHelper.DARK);
                getContext().getApplicationContext().setTheme(R.style.AppThemeDark);
                break;
        }
    }

}
