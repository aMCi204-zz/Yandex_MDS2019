package com.amciof.Yandex_MDS2019.Activities.WelcomePage;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.amciof.Yandex_MDS2019.Activities.ListOfApplications.ActivityLauncher;
import com.amciof.Yandex_MDS2019.R;
import com.amciof.Yandex_MDS2019.SPHelper;
import com.amciof.Yandex_MDS2019.SlidePagerAdapter;
import java.util.ArrayList;
import java.util.List;



public class ActivityWelcomePage
        extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentAuthor());
        fragmentList.add(new FragmentApplication());
        fragmentList.add(new FragmentTheme());
        fragmentList.add(new FragmentModel());

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new SlidePagerAdapter(getSupportFragmentManager(), fragmentList));
    }

    public void nextPage() {
        int currentPage = viewPager.getCurrentItem();
        if (currentPage == fragmentList.size() - 1) {
            SPHelper.putBoolean(this, SPHelper.WAS_STARTED, false);
            Intent intent = new Intent(this, ActivityLauncher.class);
            startActivity(intent);
            finish();
        } else {
            viewPager.setCurrentItem(currentPage + 1, true);
        }
    }
}
