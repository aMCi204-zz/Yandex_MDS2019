package com.amciof.Yandex_MDS2019.WelcomePage;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

import com.amciof.Yandex_MDS2019.R;

import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.SETTINGS_NAME;
import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.KEY_THEME;
import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.THEME_DARK;
import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.THEME_LIGHT;



public class FragmentTheme
        extends Fragment
        implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroupTheme;
    private RadioButton radioButtonLight;
    private RadioButton radioButtonDark;
    private Button buttonNext;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_3, container,false);

        radioGroupTheme = rootView.findViewById(R.id.radio_group_theme);
        radioGroupTheme.setOnCheckedChangeListener(this);

        radioButtonLight = rootView.findViewById(R.id.radio_button_light);

        radioButtonDark = rootView.findViewById(R.id.radio_button_dark);

        sharedPreferences = getActivity().getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString(KEY_THEME, THEME_LIGHT);
        switch (theme) {
            case THEME_LIGHT:
                radioButtonLight.setChecked(true);
                break;
            case THEME_DARK:
                radioButtonDark.setChecked(true);
                break;
        }

        buttonNext = rootView.findViewById(R.id.button_next);
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
        Editor editor = sharedPreferences.edit();
        switch (checkedId) {
            case R.id.radio_button_light:
                editor.putString(KEY_THEME, THEME_LIGHT);
                // TODO Мгновенное применение светлой темы
                break;
            case R.id.radio_button_dark:
                editor.putString(KEY_THEME, THEME_DARK);
                // TODO Мгновенное применение тёмной темы
                break;
        }
        editor.apply();
    }

}
