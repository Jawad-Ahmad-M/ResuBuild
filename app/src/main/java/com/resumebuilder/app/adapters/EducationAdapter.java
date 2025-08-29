package com.resumebuilder.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumebuilder.app.DatePickerBottomSheet;
import com.resumebuilder.app.R;
import com.resumebuilder.app.itemClasses.Education;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder> {
    private final List<Education> educationList;
    private final DatePickerBottomSheet datePickerBottomSheet;

    public EducationAdapter(List<Education> educationList, Context context) {
        this.educationList = educationList;
        this.datePickerBottomSheet = new DatePickerBottomSheet(context);
    }

    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_education, parent, false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {

        Education education = educationList.get(position);
        holder.universityName.setText(education.getUniversityName());
        holder.degreeTitle.setText(education.getTitleOfDegree());
        holder.location.setText(education.getLocation());
        holder.startDate.setText(education.getStartDate());
        holder.endDate.setText(education.getEndDate());

        holder.startDate.setOnClickListener(v -> datePickerBottomSheet.showForEditText(holder.startDate));
        holder.endDate.setOnClickListener(v -> datePickerBottomSheet.showForEditText(holder.endDate));

        holder.btnRemove.setOnClickListener(v -> {
            educationList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, educationList.size());
        });

    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public static class EducationViewHolder extends RecyclerView.ViewHolder {
        EditText universityName, degreeTitle, location, startDate, endDate;
        Button btnRemove;
        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            universityName = itemView.findViewById(R.id.et_university_name);
            degreeTitle = itemView.findViewById(R.id.et_degree_title_name);
            location = itemView.findViewById(R.id.et_location);
            startDate = itemView.findViewById(R.id.et_start_date);
            endDate = itemView.findViewById(R.id.et_end_date);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}