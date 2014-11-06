
package com.example.typeanything;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyNoteAdapter extends ArrayAdapter<MyNote> {

    private List<MyNote> mNotes = new ArrayList<MyNote>();

    private LayoutInflater mInflater;

    Context mContext = null;

    private static final String LOG_TAG = "MyNoteAdapter";

    public MyNoteAdapter(Context context, List<MyNote> objects) {
        super(context, 0);
        // TODO Auto-generated constructor stub
        this.mNotes = objects;
        this.mContext = context;
        Collections.reverse(mNotes);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mNotes.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.note_grid_item, null);
        }

        LinearLayout lv = (LinearLayout) view.findViewById(R.id.layout_view);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        TextView tv2 = (TextView) view.findViewById(R.id.textView2);
        view.setTag(position);
        tv.setText(mNotes.get(position).getNote());
        // tv2.setText(mNotes.get(position).getNote().substring(0,
        // 1).toUpperCase());
        tv2.setText(mNotes.get(position).getDate());

        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.d(LOG_TAG, "Inside onClick");
                MyNote note = mNotes.get((int) v.getTag());
                ((HolderFragment) ((NotesActivity) mContext).getSupportFragmentManager()
                        .findFragmentByTag(HolderFragment.LOG_TAG)).viewNote(note);
            }
        });

        return view;
    }
}
