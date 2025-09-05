package com.resumebuilder.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumebuilder.app.R;
import com.resumebuilder.app.itemClasses.Resume;

import java.util.List;

public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder>{
    private final List<Resume> resumeList;
    public ResumeAdapter(List<Resume> resumeList, Context context) {
        this.resumeList = resumeList;
    }


    @NonNull
    @Override
    public ResumeAdapter.ResumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_resume, parent, false);
        return new ResumeAdapter.ResumeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumeAdapter.ResumeViewHolder holder, int position) {
        Resume resume = resumeList.get(position);

        holder.resumeName.setText(resume.getName());
        holder.timeStamp.setText(resume.getTimestamp());

        holder.btnRemove.setOnClickListener(v -> {
            resumeList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, resumeList.size());
        });
    }

    @Override
    public int getItemCount() {
        return resumeList.size();
    }

    public static class ResumeViewHolder extends RecyclerView.ViewHolder {
        TextView timeStamp, resumeName;
        Button btnRemove;

        public ResumeViewHolder(@NonNull View itemView) {
            super(itemView);
            resumeName = itemView.findViewById(R.id.tv_resume_name);
            timeStamp = itemView.findViewById(R.id.tv_time_stamp);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }

    }
}
