package com.acnt.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by NiuKY on 11/24.
 */
public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_first);

    }

    public void seePics(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
