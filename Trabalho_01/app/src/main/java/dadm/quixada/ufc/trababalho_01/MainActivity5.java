package dadm.quixada.ufc.trababalho_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dadm.quixada.ufc.trababalho_01.fragments.PageFragment1;
import dadm.quixada.ufc.trababalho_01.fragments.PageFragment2;
import dadm.quixada.ufc.trababalho_01.fragments.PageFragment3;

public class MainActivity5 extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        List<Fragment> list = new ArrayList<>();

        list.add(new PageFragment1());
        list.add(new PageFragment2());
        list.add(new PageFragment3());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePageAdapter(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);
    }
}