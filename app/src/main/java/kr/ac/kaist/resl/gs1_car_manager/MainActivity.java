package kr.ac.kaist.resl.gs1_car_manager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // FloatingActionButton 나중에 지우기
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Under preparation", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // db part
        //dbHelper = new DBHelper(this.getApplicationContext(), "Test3.db", null, 1);
        //DBContents dbContents = new DBContents();
        //dbHelper.drop("car_info");
        //dbHelper.drop("product");
        //dbHelper.drop("repair_shop");
        //dbContents.fillDB_car_info(dbHelper);
        //dbContents.fillDB_product(dbHelper);
        //dbContents.fillDB_repair_shop(dbHelper);
        //Log.w("repair_shop", "result:" + dbHelper.getResult_repair_shop());
        //Log.w("car_info", "result:" + dbHelper.getResult_car_info());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
