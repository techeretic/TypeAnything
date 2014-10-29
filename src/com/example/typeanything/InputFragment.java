/**
 * 
 */

package com.example.typeanything;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author p.shetye
 */
public class InputFragment extends Fragment {

    private static final String LOG_TAG = "InputFragment";

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
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        
        ((NotesActivity)getActivity()).setABColors(Color.rgb(191, 207, 0));
        EditText textView = (EditText) getView().findViewById(R.id.editText1);
        
        /*int cx = (textView.getLeft() + textView.getRight()) / 2;
        int cy = (textView.getTop()  + textView.getBottom()) / 2;

        Animator anim = ViewAnimationUtils.
                createCircularReveal(textView, cx, cy, 0, textView.getWidth());
        
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                EditText ed = (EditText) getView().findViewById(R.id.editText1);
                ed.setVisibility(View.VISIBLE);
            }
        });

        anim.start();*/
        
        textView.requestFocus();
		
        Log.d(LOG_TAG, "Inside onAttach");

        if (textView != null)
            Log.d(LOG_TAG, "textView.callOnClick() = " + textView.callOnClick());
    }
}
