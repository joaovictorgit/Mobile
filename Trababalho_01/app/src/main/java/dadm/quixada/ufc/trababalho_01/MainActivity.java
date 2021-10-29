package dadm.quixada.ufc.trababalho_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dadm.quixada.ufc.trababalho_01.fragments.PageFragment1;
import dadm.quixada.ufc.trababalho_01.fragments.PageFragment2;
import dadm.quixada.ufc.trababalho_01.fragments.PageFragment3;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    String [] menuName = {"Som","Pager","ListView","AutoComplete","Spinner","Buttons","PopUp"};
    int[] menuImage = {R.drawable.som,R.drawable.page,R.drawable.list,R.drawable.lupa,R.drawable.dropdown, R.drawable.buttons, R.drawable.menus};
    int kalimba = R.drawable.kalimba;
    MediaPlayer mySound;

    @Override
    protected void onPause(){
        super.onPause();
        mySound.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySound = MediaPlayer.create(this, R.raw.kalimba);
        gridView = findViewById(R.id.gridView);
        CustomAdapter customAdapter = new CustomAdapter();

        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Intent intent;/* = new Intent(getApplicationContext(), GridItemActivity.class);
                intent.putExtra("name", menuName[i]);
                intent.putExtra("image", menuImage[i]);
                */
                if(menuName[i].equals("Som")){
                    playMusic(view);
                }
                else if(menuName[i].equals("AutoComplete")){
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
                else if(menuName[i].equals("ListView")){
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                }
                else if(menuName[i].equals("Spinner")){
                    Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                    startActivity(intent);
                }
                else if(menuName[i].equals("Pager")){
                    Intent intent = new Intent(MainActivity.this, MainActivity5.class);
                    startActivity(intent);
                }else if(menuName[i].equals("Buttons")){
                    Intent intent = new Intent(MainActivity.this, MainActivity6.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(MainActivity.this, MainActivity7.class);
                    startActivity(intent);
                }
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return menuImage.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data, null);
            TextView name = view1.findViewById(R.id.menus);
            ImageView image = view1.findViewById(R.id.images);

            name.setText(menuName[i]);
            image.setImageResource(menuImage[i]);

            return view1;
        }
    }




    public void playMusic(View view){
        mySound.start();
    }
}