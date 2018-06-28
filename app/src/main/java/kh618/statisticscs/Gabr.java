package kh618.statisticscs;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Gabr extends android.support.v4.app.Fragment {
    //get 'Linear ALgabr ' lecture and section

    ViewPag pag;
    SubjectAdabter adabter;
    SubjectAdabter adabter2;
    InternalDB db;
    View v;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.activity_gabr, container, false);
        db = new InternalDB(v.getContext());
        adabter = new SubjectAdabter(getContext(), R.layout.subject_list, db.getLinear_Algebra("Lecture"));
        adabter2 = new SubjectAdabter(getContext(), R.layout.subject_list, db.getLinear_Algebra("Section"));

         viewPager = v.findViewById(R.id.view);
        pag = new ViewPag(v.getContext(), adabter);
        viewPager.setAdapter(pag);
          tabLayout = v.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.textColor));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == 0){
                    pag.setAdabter(adabter);
                    if(adabter.getCount() <= 0)
                        Snackbar.make(v, "No " + tab.getText() + " found", Snackbar.LENGTH_SHORT).show();
                }
                else if(tab.getPosition() == 1){
                    pag.setAdabter(adabter2);
                    if(adabter2.getCount() <= 0)
                        Snackbar.make(v, "No " + tab.getText() + " found", Snackbar.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //  imageView.setImageResource(R.drawable.slide2);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // imageView.setImageResource(R.drawable.slide3);
            }
        });

        return v;
    }
}