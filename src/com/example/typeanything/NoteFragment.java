/**
 * 
 */

package com.example.typeanything;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import java.util.Random;

/**
 * @author p.shetye
 */
public class NoteFragment extends Fragment {

    public static final String LOG_TAG = "NoteFragment";

    private String noteContent = null;

    private MyNote mNote = null;

    // Container Activity must implement this interface
    public interface OnNoteClickedListener {
        public void showClickedButtons();

        public void hideClickedButtons();

        public void onActionPerformed();
    };

    OnNoteClickedListener mCallBack;

    /**
     * 
     */
    public NoteFragment() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.note_fragment, container, false);
        Log.d(LOG_TAG, "Inside onCreateView");

        mNote = (MyNote) getArguments().getParcelable("Note");
        noteContent = mNote.getNote();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);

        try {
            mCallBack = (OnNoteClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnNoteClickedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        Log.d(LOG_TAG, "Inside onActivityCreated");

        Random randomGenerator = new Random();
        int r = randomGenerator.nextInt(255);
        int g = randomGenerator.nextInt(255);
        int b = randomGenerator.nextInt(255);
        ((NotesActivity) getActivity()).setABColors(Color.argb(128, r, g, b));

        View myView = getView();

        if (myView.isAttachedToWindow()) {
            // get the center for the clipping circle
            int cx = (myView.getLeft() + myView.getRight()) / 2;
            int cy = (myView.getTop() + myView.getBottom()) / 2;

            // get the final radius for the clipping circle
            int finalRadius = myView.getWidth();

            // create and start the animator for this view
            // (the start radius is zero)
            Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
            anim.setInterpolator(new AccelerateDecelerateInterpolator());
            anim.start();
        }

        TextView textView = (TextView) getView().findViewById(R.id.noteview);
        textView.setText(noteContent);
        textView.setMovementMethod(new ScrollingMovementMethod());

        if (textView != null)
            Log.d(LOG_TAG, "textView.callOnClick() = " + textView.callOnClick());
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (mCallBack != null) {
            mCallBack.showClickedButtons();
        }
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (mCallBack != null) {
            mCallBack.hideClickedButtons();
        }
    }

    public void shareNote() {
        Log.d(LOG_TAG, "Inside ShareNote");
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "From - Type Anything");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, mNote.getNote());
        startActivity(Intent.createChooser(sharingIntent, "Choose option"));
        /*
         * if (mCallBack != null) { mCallBack.onActionPerformed(); }
         */
    }

    public void deleteNote() {
        Log.d(LOG_TAG, "Inside DeleteNote");
        NotesActivity.db.deleteNote(mNote);
        if (mCallBack != null) {
            mCallBack.onActionPerformed();
        }
    }
}
