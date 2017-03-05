package com.example.ravi.depaul.lessons;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.example.ravi.depaul.R;


import java.util.List;

public class ClassAdapter extends ExpandableRecyclerAdapter<ClassViewHolder, SubjectViewHolder> {

    private LayoutInflater mInflator;
    Context ctx;

    public ClassAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
        ctx=context;
    }

    @Override
    public ClassViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View recipeView = mInflator.inflate(R.layout.les_class_view, parentViewGroup, false);
        return new ClassViewHolder(recipeView);

    }

    @Override
    public SubjectViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View ingredientView = mInflator.inflate(R.layout.les_subjects_view, childViewGroup, false);
        return new SubjectViewHolder(ingredientView);
    }

    @Override
    public void onBindParentViewHolder(final ClassViewHolder classViewHolder, int position, ParentListItem parentListItem) {
        Class aClass = (Class) parentListItem;
        classViewHolder.bind(aClass);

    }

    @Override
    public void onBindChildViewHolder(final SubjectViewHolder subjectViewHolder, final int position, Object childListItem) {
        Subject subject = (Subject) childListItem;

        subjectViewHolder.bind(subject);
        subjectViewHolder.mIngredientTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ctx,subjectViewHolder.mIngredientTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
