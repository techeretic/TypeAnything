/**
 * 
 */

package com.example.typeanything;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Random;

/**
 * @author p.shetye
 */
public class InputFragment extends Fragment {

    public static final String LOG_TAG = "InputFragment";

    // Container Activity must implement this interface
    public interface OnNoteSavedListener {
        public void onNoteSaved();
        public void showSaveButton();
        public void hideSaveButton();
    };

    OnNoteSavedListener mCallBack;

    /**
	 * 
	 */
    public InputFragment() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.input_fragment, container, false);
        Log.d(LOG_TAG, "Inside onCreateView");
        EditText textView = (EditText) view.findViewById(R.id.editText1);

        textView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d(LOG_TAG, "INSIDE OnClick");
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);

        try {
            mCallBack = (OnNoteSavedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnNoteSavedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        Random randomGenerator = new Random();
        int r = randomGenerator.nextInt(255);
        int g = randomGenerator.nextInt(255);
        int b = randomGenerator.nextInt(255);
        //((NotesActivity) getActivity()).setABColors(Color.rgb(191, 207, 0));
        ((NotesActivity) getActivity()).setABColors(Color.argb(128, r, g, b));
        EditText textView = (EditText) getView().findViewById(R.id.editText1);

        textView.requestFocus();

        Log.d(LOG_TAG, "Inside onAttach");

        if (textView != null)
            Log.d(LOG_TAG, "textView.callOnClick() = " + textView.callOnClick());
    }

    public void saveNote() {
        EditText textView = (EditText) getView().findViewById(R.id.editText1);
        MyNote note = new MyNote((int) System.currentTimeMillis(), textView.getText().toString());
        NotesActivity.db.addNote(note);
        textView.setText("");
        if (mCallBack != null) {
            mCallBack.onNoteSaved();
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (mCallBack != null) {
            mCallBack.showSaveButton();
        }
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (mCallBack != null) {
            mCallBack.hideSaveButton();
        }
    }
}
