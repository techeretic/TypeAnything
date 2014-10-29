
package com.example.typeanything;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class NotesActivity extends ActionBarActivity {

    private static final String LOG_TAG = "ActionBarActivity";

    private FloatingActionButton fabButton = null;
    
    private HolderFragment mHFrag = null;
    
    private InputFragment mIFrag = null;
    
    private enum Frags {
    	Holder,
    	Input,
    };

    private static Frags sWhichFrag = Frags.Holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG,"Inside onCreate()");
        setContentView(R.layout.activity_notes);

        fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_new))
                .withButtonColor(Color.GRAY)
                .withGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL).withMargins(0, 0, 0, 16)
                .create();

        fabButton.animate();

        fabButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                displayView(Frags.Input);
            }
        });

        displayView(Frags.Holder);
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

    private void displayView(Frags frag) {
        
        Log.d(LOG_TAG,"frag == " + frag.toString());
        // update the main content by replacing fragments

        FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.setCustomAnimations(R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom);
        switch(frag) {
        	case Holder:
        		Log.d(LOG_TAG, "In CASE HOLDER");
                fabButton.setVisibility(View.VISIBLE);
        		if (mHFrag == null){
        			Log.d(LOG_TAG, "mHFrag == null");
        			mHFrag = new HolderFragment();
        		}
        		sWhichFrag = Frags.Holder;
        		ft.replace(R.id.container, mHFrag).commit();
        		break;
        	case Input:
        		Log.d(LOG_TAG, "In CASE INPUT");
                fabButton.setVisibility(View.INVISIBLE);
        		if (mIFrag == null) {
        			Log.d(LOG_TAG, "mIFrag == null");
        			mIFrag = new InputFragment();
        		}
        		sWhichFrag = Frags.Input;
        		ft.replace(R.id.container, mIFrag).commit();
        		break;
        }

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (sWhichFrag == Frags.Input) {
            displayView(Frags.Holder);
        } else {
            super.onBackPressed();
        }
    }

    public void setABColors(int color) {
        getWindow().setStatusBarColor(color);

        ColorDrawable colorDrawable = new ColorDrawable(color);
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }
}
