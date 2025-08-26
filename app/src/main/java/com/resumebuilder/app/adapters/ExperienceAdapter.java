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
import com.resumebuilder.app.itemClasses.Experience;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder> {
    private final List<Experience> experienceList;
    private final DatePickerBottomSheet datePickerBottomSheet;

    public ExperienceAdapter(List<Experience> experienceList, Context context) {
        this.experienceList = experienceList;
        this.datePickerBottomSheet = new DatePickerBottomSheet(context);
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_experience, parent, false);
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Experience experience = experienceList.get(position);

        // Safely bind data to avoid null pointer exceptions
        holder.jobRole.setText(experience.getJobRole() != null ? experience.getJobRole() : "");
        holder.companyName.setText(experience.getCompanyName() != null ? experience.getCompanyName() : "");
        holder.location.setText(experience.getLocation() != null ? experience.getLocation() : "");
        holder.startDate.setText(experience.getStartDate() != null ? experience.getStartDate() : "");
        holder.endDate.setText(experience.getEndDate() != null ? experience.getEndDate() : "");
        holder.jobDesc.setText(experience.getJobDescription() != null ? experience.getJobDescription() : "");

        holder.startDate.setOnClickListener(v ->
                datePickerBottomSheet.showForEditText(holder.startDate)
        );

        holder.endDate.setOnClickListener(v ->
                datePickerBottomSheet.showForEditText(holder.endDate)
        );

        holder.btnRemove.setOnClickListener(v -> {
            experienceList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, experienceList.size());
        });
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    public static class ExperienceViewHolder extends RecyclerView.ViewHolder {
        EditText jobRole, companyName, location, startDate, endDate, jobDesc;
        Button btnRemove;

        public ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            jobRole = itemView.findViewById(R.id.et_job_title);
            companyName = itemView.findViewById(R.id.et_company);
            location = itemView.findViewById(R.id.et_location);
            startDate = itemView.findViewById(R.id.et_start_date);
            endDate = itemView.findViewById(R.id.et_end_date);
            jobDesc = itemView.findViewById(R.id.et_job_description);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
