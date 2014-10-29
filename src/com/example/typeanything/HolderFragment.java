/**
 * 
 */

package com.example.typeanything;

import android.app.Activity;
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
 */
public class HolderFragment extends Fragment {

    public static final String LOG_TAG = "HolderFragment";

    // Container Activity must implement this interface
    public interface OnResumeListener {
        public void showAddButton();
        public void hideAddButton();
    };

    OnResumeListener mCallBack;

    /**
     * 
     */
    public HolderFragment() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);

        try {
            mCallBack = (OnResumeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnResumeListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.holder_fragment, container, false);
        Log.d(LOG_TAG, "Inside onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        ((NotesActivity) getActivity()).setABColors(Color.rgb(199, 36, 36));
        
        NotesActivity.hideSoftKeyboard(getActivity(), getView());
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (mCallBack != null) {
            mCallBack.showAddButton();
        }
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (mCallBack != null) {
            mCallBack.hideAddButton();
        }
    }
}
