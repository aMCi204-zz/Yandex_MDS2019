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



public class FragmentModel
        extends Fragment
        implements OnCheckedChangeListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_4, container,false);

        RadioGroup radioGroupModel = rootView.findViewById(R.id.radio_group_model);
        radioGroupModel.setOnCheckedChangeListener(this);

        RadioButton radioButtonStandart = rootView.findViewById(R.id.radio_button_standart);

        RadioButton radioButtonDense = rootView.findViewById(R.id.radio_button_dense);

        String theme = SPHelper.getString(rootView.getContext(), SPHelper.MODEL, SPHelper.STANDART);
        switch (theme) {
            case SPHelper.STANDART:
                radioButtonStandart.setChecked(true);
                break;
            case SPHelper.DENSE:
                radioButtonDense.setChecked(true);
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
            case R.id.radio_button_standart:
                SPHelper.putString(getContext(), SPHelper.MODEL, SPHelper.STANDART);
                break;
            case R.id.radio_button_dense:
                SPHelper.putString(getContext(), SPHelper.MODEL, SPHelper.DENSE);
                break;
        }
    }
}
