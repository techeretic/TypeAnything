package com.example.typeanything;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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

    public MyNoteAdapter(Context context, List<MyNote> objects) {
        super(context, 0);
        // TODO Auto-generated constructor stub
        this.mNotes = objects;
        Collections.reverse(mNotes);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        if ( convertView == null )
        {
            view = mInflater.inflate(R.layout.note_grid_item, null);
        }
        
        LinearLayout lv = (LinearLayout) view.findViewById(R.id.layout_view);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        TextView tv2 = (TextView) view.findViewById(R.id.textView2);
        tv.setText(mNotes.get(position).getNote());
        //tv2.setText(mNotes.get(position).getNote().substring(0, 1).toUpperCase());
        tv2.setText(mNotes.get(position).getDate());
        tv2.setTextSize(8);
        tv.setTextSize(25);
        /*
        Random randomGenerator = new Random();
        int r = randomGenerator.nextInt(255);
        int g = randomGenerator.nextInt(100);
        int b = randomGenerator.nextInt(50);
        
        tv.setTextColor(Color.rgb(r, g, b));
        
        int[][] states = new int[][] { new int[] { android.R.attr.state_focused, android.R.attr.state_pressed, android.R.attr.state_enabled } };

        int[] colors = new int[] { Color.rgb(r,g,b), Color.rgb((255-r),(g+100),(b+50)), Color.rgb(255-r,255-g,255-b) };

        view.setBackground(new RippleDrawable(new ColorStateList(states,colors),
        		null, null));
        
        view.setElevation(10);
        
        lv.setBackgroundColor(Color.rgb((255-r),(255-g),(255-b)));
        */
        /*if (tv.getText().toString().length() > 10)
            tv.setTextSize(15);
        else
            tv.setTextSize(30);*/
        
        return view;
    }
}