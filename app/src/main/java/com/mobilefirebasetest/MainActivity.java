package com.mobilefirebasetest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    Bundle bundle;
    Bundle appOpenBundle;
    private String spenderType = "small";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        bundle = new Bundle();
        String id = "sean id";
        String name = "sean name";
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);

        appOpenBundle = new Bundle();
        appOpenBundle.putString(FirebaseAnalytics.Param.ORIGIN, "firebase test app open");


        final String genderType = "male";
        final String ageVal = "30";


        mFirebaseAnalytics.setUserProperty("Age", ageVal);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, MainActivity.this.bundle);
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, MainActivity.this.appOpenBundle);
                mFirebaseAnalytics.setUserProperty("Gender", genderType);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.spender_small) {
            MainActivity.this.spenderType = "small";
            mFirebaseAnalytics.setUserProperty("spender", spenderType);
            return true;
        }
        if (id == R.id.spender_big) {
            this.spenderType = "big";
            mFirebaseAnalytics.setUserProperty("spender", spenderType);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
