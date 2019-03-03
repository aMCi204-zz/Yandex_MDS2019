package com.amciof.Yandex_MDS2019.WelcomePage;


import android.content.Context;
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
import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.KEY_MODEL;
import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.MODEL_DENSE;
import static com.amciof.Yandex_MDS2019.ListOfApplications.ActivityLauncher.MODEL_STANDART;



public class FragmentModel
        extends Fragment
        implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroupModel;
    private RadioButton radioButtonStandart;
    private RadioButton radioButtonDense;
    private Button buttonNext;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_4, container,false);

        radioGroupModel = rootView.findViewById(R.id.radio_group_model);
        radioGroupModel.setOnCheckedChangeListener(this);

        radioButtonStandart = rootView.findViewById(R.id.radio_button_standart);

        radioButtonDense = rootView.findViewById(R.id.radio_button_dense);

        sharedPreferences = getActivity().getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        String theme = sharedPreferences.getString(KEY_MODEL, MODEL_STANDART);
        switch (theme) {
            case MODEL_STANDART:
                radioButtonStandart.setChecked(true);
                break;
            case MODEL_DENSE:
                radioButtonDense.setChecked(true);
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
            case R.id.radio_button_standart:
                editor.putString(KEY_MODEL, MODEL_STANDART);
                break;
            case R.id.radio_button_dense:
                editor.putString(KEY_MODEL, MODEL_DENSE);
                break;
        }
        editor.apply();
    }
}
