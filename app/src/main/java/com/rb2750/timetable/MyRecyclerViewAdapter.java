package com.rb2750.timetable;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<DataObject> mDataset;
    private static LongClickListener longClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnLongClickListener {
        TextView subject;
        TextView teacher;
        TextView startTime;
        TextView length;
        TextView classLocation;
        View view;

        public DataObjectHolder(View itemView) {
            super(itemView);
            view = itemView;
            subject = (TextView) itemView.findViewById(R.id.cardSubjectText);
            teacher = (TextView) itemView.findViewById(R.id.cardTeacherText);
            startTime = (TextView) itemView.findViewById(R.id.cardTimeText);
            length = (TextView) itemView.findViewById(R.id.cardPeriodLengthText);
            classLocation = (TextView) itemView.findViewById(R.id.cardClassLocation);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            longClickListener.onItemLongClick(getAdapterPosition(), v);
            return true;
        }
    }

    void setOnItemLongClickListener(LongClickListener myClickListener) {
        longClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_card_view, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.subject.setText(mDataset.get(position).getSubject());
        holder.teacher.setText(mDataset.get(position).getTeacher());
        holder.startTime.setText(String.format(Locale.UK, "%02d:%02d", mDataset.get(position).getStartHour(), mDataset.get(position).getStartMinute()));
        holder.length.setText(String.format(Locale.UK, "%02d:%02d", mDataset.get(position).getEndHour(), mDataset.get(position).getEndMinute()));
        String classLoc = mDataset.get(position).getClassLocation();
        holder.classLocation.setText(!classLoc.isEmpty() ?/* "Room: \n" + */classLoc : "");
        ((CardView) holder.view.findViewById(R.id.card_view)).setCardBackgroundColor(mDataset.get(position).getColor());
        if (mDataset.get(position).getColor() == Color.parseColor("#E0E0E0"))
            ((CardView) holder.view.findViewById(R.id.card_view)).setCardElevation(0f);
    }

    public void addItem(DataObject dataObj/*, int index*/) {
        mDataset.add(/*index, */dataObj);
        notifyItemInserted(mDataset.size() - 1/*index*/);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface LongClickListener {
        public void onItemLongClick(int position, View v);
    }
}