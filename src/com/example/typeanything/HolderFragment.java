/**
 * 
 */
package com.example.typeanything;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author p.shetye
 *
 */
public class HolderFragment extends Fragment {
	
	private static final String LOG_TAG = "HolderFragment";

    /**
     * 
     */
    public HolderFragment() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.holder_fragment,
                container, false);
        Log.d(LOG_TAG, "Inside onCreateView");
        return view;
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	
    	((NotesActivity)getActivity()).setABColors(Color.rgb(199, 36, 36));
    }
}
