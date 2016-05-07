package com.example.dana.final_project;

import android.app.ListActivity;
import android.media.Rating;
import android.os.Bundle;
import android.renderscript.Type;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Dana on 5/6/2016.
 */
public class DatabaseActivity extends ListActivity {

        private ActivityDataSource datasource;      // our link to the datasource for SQLite access
        private int listPosition = 0;                // currently selected item in the list

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tab_fragment_2);

            datasource = new ActivityDataSource(this);
            datasource.open();

            List<Activity> values = datasource.getAllActivities();

            // use the SimpleCursorAdapter to show the elements in a ListView
            ArrayAdapter<Activity> adapter = new ArrayAdapter<Activity>(this,
                    android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);

            // need to grab the list item click so we remember what item is selected
            ListView lv = (ListView) findViewById(android.R.id.list);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
                {
                    listPosition = position;
                    //Toast.makeText(SuggestionActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

        // Will be called via the onClick attribute
        // of the buttons in main.xml
        public void onClick(View view) {
            @SuppressWarnings("unchecked")
            ArrayAdapter<Activity> myListAdapter = (ArrayAdapter<Activity>) getListAdapter();
            Activity activity = null;
            Type type = null;
            switch (view.getId()) {
                case R.id.add:
                    //String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                    //int nextInt = new Random().nextInt(3);
                    // save the new comment to the database

                    // Create variables for UI components
                    EditText txtActivity = (EditText) findViewById(R.id.etActivity);
                    EditText txtType = (EditText) findViewById(R.id.txtType);

                    activity = datasource.createActivity(txtActivity.getText().toString(),
                            txtType.getText().toString());

                    myListAdapter.add(activity);
                    txtActivity.setText("");
                    txtType.setText("");

                    break;
                case R.id.delete:
                    // The selected item position is stored in the variable listPosition by the onItemClick listener
                    if (myListAdapter.getCount() > listPosition) {
                        activity = (Activity) getListAdapter().getItem(listPosition);
                        datasource.deleteActivity(activity);
                        myListAdapter.remove(activity);
                    }
                    break;
            }
            myListAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onResume() {
            datasource.open();
            super.onResume();
        }

        @Override
        protected void onPause() {
            datasource.close();
            super.onPause();
        }

}

