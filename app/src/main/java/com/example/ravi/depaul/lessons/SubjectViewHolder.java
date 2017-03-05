package com.example.ravi.depaul.lessons;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.ravi.depaul.R;

public class SubjectViewHolder extends ChildViewHolder {

    public TextView mIngredientTextView;

    public SubjectViewHolder(View itemView) {
        super(itemView);
        mIngredientTextView = (TextView) itemView.findViewById(R.id.ingredient_textview);
        Log.d("TAAAAAAAAAAAAAAAAA","IngViewHolder");

    }

    public void bind(Subject subject) {
        mIngredientTextView.setText(subject.getName());
    }
}
