
package com.example.typeanything;

import java.util.Random;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Fade;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;


public class NotesActivity extends ActionBarActivity implements InputFragment.OnNoteSavedListener,
        HolderFragment.OnResumeListener {

    private static final String LOG_TAG = "ActionBarActivity";

    private FloatingActionButton fab_add_btn = null;

    private FloatingActionButton fab_save_btn = null;

    public static HolderFragment mHFrag = null;

    public static InputFragment mIFrag = null;

    public static DatabaseHelper db;
    
    public enum Fragments {
        Holder,
        Input
    };
    
    private static Fragments sCurrentFrag = Fragments.Holder; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Inside onCreate()");
        setContentView(R.layout.activity_notes);
        
        Random randomGenerator = new Random();
        int r = randomGenerator.nextInt(255);
        int g = randomGenerator.nextInt(255);
        int b = randomGenerator.nextInt(255);

        db = new DatabaseHelper(this);

        fab_add_btn = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_new))
                .withButtonColor(Color.rgb(r,g,b))
                .withGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL).withMargins(0, 0, 0, 15)
                .create();

        fab_add_btn.animate();

        fab_add_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                displayFragment(mIFrag, InputFragment.LOG_TAG);
            }
        });

        fab_save_btn = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_save))
                .withButtonColor(Color.rgb(r,g,b)).withGravity(Gravity.TOP | Gravity.END)
                .withMargins(0, 10, 0, 0).create();

        fab_save_btn.animate();

        fab_save_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ((InputFragment) getSupportFragmentManager().findFragmentByTag(
                        InputFragment.LOG_TAG)).saveNote();

                if (fab_save_btn != null) {
                    fab_save_btn.setVisibility(View.INVISIBLE);
                }
            }
        });

        fab_save_btn.setVisibility(View.INVISIBLE);

        displayFragment(mHFrag, HolderFragment.LOG_TAG);
        getWindow().setExitTransition(new Fade());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displayFragment(Fragment frag, String tag) {

        // update the main content by replacing fragments
        if (frag == null) {
            if (tag.equals(HolderFragment.LOG_TAG)) {
                sCurrentFrag = Fragments.Holder;
                frag = new HolderFragment();
            } else if (tag.equals(InputFragment.LOG_TAG)) {
                sCurrentFrag = Fragments.Input;
                frag = new InputFragment();
            }
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
        ft.replace(R.id.container, frag, tag);
        ft.commit();
        Log.d(LOG_TAG, "IN displayFragment | BackStackEntry Count = "
                + getSupportFragmentManager().getBackStackEntryCount());
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (sCurrentFrag == Fragments.Input){
            Log.d(LOG_TAG, "Gonna Show HolderFragment");
            displayFragment(mHFrag, HolderFragment.LOG_TAG);
        } else {
            super.onBackPressed();
        }

        Log.d(LOG_TAG, "IN onBackPressed | BackStackEntry Count = "
                + getSupportFragmentManager().getBackStackEntryCount());
        /*
         * if (sWhichFrag == Frags.Input) { displayFragment(mHFrag); } else {
         * super.onBackPressed(); }
         */
    }

    public void setABColors(int color) {
        getWindow().setStatusBarColor(color);

        ColorDrawable colorDrawable = new ColorDrawable(color);
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setElevation(5);
    }

    @Override
    public void onNoteSaved() {
        // TODO Auto-generated method stub
        Log.d(LOG_TAG, "Inside onNoteSaved");
        displayFragment(mHFrag, HolderFragment.LOG_TAG);
    }

    @Override
    public void showAddButton() {
        // TODO Auto-generated method stub
        if (fab_add_btn != null)
            fab_add_btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAddButton() {
        // TODO Auto-generated method stub
        if (fab_add_btn != null)
            fab_add_btn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSaveButton() {
        // TODO Auto-generated method stub
        if (fab_save_btn != null)
            fab_save_btn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSaveButton() {
        // TODO Auto-generated method stub
        if (fab_save_btn != null)
            fab_save_btn.setVisibility(View.INVISIBLE);
    }

    public static void hideSoftKeyboard (Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

}
