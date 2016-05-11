package com.example.dana.final_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Created by Dana McGree, Ashley Stalvig, Ted Jacobi on 4/29/2016.
 * Tab code/layout based on http://javapapers.com/android/android-tab-layout-tutorial/
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Welcome"));
        tabLayout.addTab(tabLayout.newTab().setText("Weight"));
        tabLayout.addTab(tabLayout.newTab().setText("Recipies"));
        tabLayout.addTab(tabLayout.newTab().setText("Results"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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


// open recipe website links based on various Google searches and code from Master Detail assignment
    public void browser1 (View view) {

        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://allrecipes.com/recipes/84/healthy-recipes/"));
        startActivity(browserIntent);
    }
    public void browser2 (View view) {

        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.foodnetwork.com/healthy.html"));
        startActivity(browserIntent);
    }
    public void browser3 (View view) {

        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cookinglight.com/food/quick-healthy-recipes"));
        startActivity(browserIntent);
    }
    public void browser4 (View view) {

        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.eatingwell.com/"));
        startActivity(browserIntent);
    }
//share results based on information from this site https://androidsolved.wordpress.com/2015/07/22/share-option-or-button-implementation-example-in-android-using-android-studio/
    public void shareIt (View view) {

        //Get the selected text
        EditText et=(EditText)findViewById(R.id.message);
        int startSelection=et.getSelectionStart();
        int endSelection=et.getSelectionEnd();
        String selectedText = et.getText().toString().substring(startSelection, endSelection);

        //if no text is selected share the entire text area.
        if(selectedText.length() == 0){
            EditText message = (EditText) findViewById(R.id.message);
            String dataToShare = message.getText().toString();
            selectedText = dataToShare;
        }

        //Share the text
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, selectedText);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }


}

