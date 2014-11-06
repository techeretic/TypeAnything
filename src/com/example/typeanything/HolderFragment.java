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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author p.shetye
 */
public class HolderFragment extends Fragment {

    public static final String LOG_TAG = "HolderFragment";

    private static List<MyNote> sMyNotes = new ArrayList<MyNote>();

    MyNoteAdapter mNoteAdapter = null;

    // Container Activity must implement this interface
    public interface OnResumeListener {
        public void showAddButton();

        public void hideAddButton();

        public void noteSelected(MyNote note);
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

        sMyNotes = NotesActivity.db.getAllNotes();

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
        Random randomGenerator = new Random();
        int r = randomGenerator.nextInt(255);
        int g = randomGenerator.nextInt(255);
        int b = randomGenerator.nextInt(255);
        // ((NotesActivity) getActivity()).setABColors(Color.rgb(199, 36, 36));

        ((NotesActivity) getActivity()).setABColors(Color.argb(128, r, g, b));

        NotesActivity.hideSoftKeyboard(getActivity(), getView());

        Log.d(LOG_TAG, "sMyNotes size = " + sMyNotes.size());

        mNoteAdapter = new MyNoteAdapter((NotesActivity) getActivity(), sMyNotes);

        ListView gv = (ListView) getView().findViewById(R.id.ListView);
        gv.setAdapter(mNoteAdapter);
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

    public void viewNote(MyNote note) {
        if (mCallBack != null) {
            mCallBack.noteSelected(note);
        }
    }
}
