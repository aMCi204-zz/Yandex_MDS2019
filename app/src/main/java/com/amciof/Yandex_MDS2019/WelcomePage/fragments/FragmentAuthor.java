package com.amciof.Yandex_MDS2019.WelcomePage.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amciof.Yandex_MDS2019.R;
import com.amciof.Yandex_MDS2019.WelcomePage.ActivityWelcomePage;

public class FragmentAuthor extends Fragment {

    private Button buttonNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.page_1, container,false);

        buttonNext = rootView.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ActivityWelcomePage)getActivity()).nextPage();
            }
        });

        return rootView;
    }
}
