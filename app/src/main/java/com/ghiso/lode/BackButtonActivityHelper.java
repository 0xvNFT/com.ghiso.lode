package com.ghiso.lode;

import android.app.Activity;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BackButtonActivityHelper {

    private final Activity activity;
    private ActionBar actionBar;

    public BackButtonActivityHelper(Activity activity) {
        this.activity = activity;
        if (activity instanceof AppCompatActivity) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            actionBar = appCompatActivity.getSupportActionBar();
        }
    }

    public void enableBackButton() {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            activity.onBackPressed();
            return true;
        }
        return false;
    }
}
