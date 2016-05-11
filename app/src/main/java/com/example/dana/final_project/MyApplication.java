/**
 * Created by Dana McGree, Ashley Stalvig, Ted Jacobi on 4/29/2016.
 * Tab code/layout based on http://javapapers.com/android/android-tab-layout-tutorial/
 */
package com.example.dana.final_project;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static Context context;

    public void onCreate(){
        MyApplication.context=getApplicationContext();
    }

    /**
     * This Method will always return the application Context.
     * 
     * @return Context
     */
    public static Context getAppContext() {
    	return context;
    }
}
