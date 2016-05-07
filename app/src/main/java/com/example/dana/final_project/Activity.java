package com.example.dana.final_project;

/**
 * Created by Dana McGree, Ashley Stalvig, Ted Jacobi on 4/29/2016.
 */
public class Activity {
    private long id;
    private String activity;
    private String type;

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

    public String getType() {

        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {

        return activity + type;
    }
}
