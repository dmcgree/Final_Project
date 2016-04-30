package com.example.dana.final_project;

/**
 * Created by Dana on 4/29/2016.
 */
public class Activity {
        private long id;
        private String activity;

        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }

        public String getActivity() {
            return activity;
        }
        public void setActivity(String activity) {
            this.activity = activity;
        }


        // Will be used by the ArrayAdapter in the ListView
        @Override
        public String toString() {
            return activity;
        }
    }
