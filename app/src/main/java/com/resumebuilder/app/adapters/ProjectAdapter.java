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
import com.resumebuilder.app.itemClasses.Project;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>{

    private final List<Project> projectList;
    private final DatePickerBottomSheet datePickerBottomSheet;


    public ProjectAdapter(List<Project> projectList, Context context) {
        this.projectList = projectList;
        this.datePickerBottomSheet = new DatePickerBottomSheet(context);
    }

    @NonNull
    @Override
    public ProjectAdapter.ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        return new ProjectAdapter.ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ProjectViewHolder holder, int position) {
        Project project = projectList.get(position);

        holder.titleOfProject.setText(project.getTitleOfProject());
        holder.organization.setText(project.getOrganization());
        holder.startDate.setText(project.getStartDate());
        holder.endDate.setText(project.getEndDate());
        holder.projectDesc.setText(project.getProjectDesc());

        holder.startDate.setOnClickListener(v -> datePickerBottomSheet.showForEditText(holder.startDate));

        holder.endDate.setOnClickListener(v -> datePickerBottomSheet.showForEditText(holder.endDate));

        holder.btnRemove.setOnClickListener(v -> {
            projectList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, projectList.size());
        });

    }


    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public void updateProjectListFromInputs() {
        for (int i = 0; i < getItemCount(); i++) {
            ProjectAdapter.ProjectViewHolder holder = (ProjectAdapter.ProjectViewHolder) bindingRecyclerView.findViewHolderForAdapterPosition(i);
            if (holder != null) {
                String titleOfProject = holder.titleOfProject.getText().toString().trim();
                String organization = holder.organization.getText().toString().trim();
                String startDate = holder.startDate.getText().toString().trim();
                String endDate = holder.endDate.getText().toString().trim();
                String projectDesc = holder.projectDesc.getText().toString().trim();

                projectList.get(i).setTitleOfProject(titleOfProject);
                projectList.get(i).setOrganization(organization);
                projectList.get(i).setStartDate(startDate);
                projectList.get(i).setEndDate(endDate);
                projectList.get(i).setProjectDesc(projectDesc);
            }
        }
    }

    // 📌 Needs to be set from the Fragment to access RecyclerView reference
    private RecyclerView bindingRecyclerView;
    public void setRecyclerView(RecyclerView recyclerView) {
        this.bindingRecyclerView = recyclerView;
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        EditText titleOfProject, organization, startDate, endDate, projectDesc;
        Button btnRemove;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOfProject = itemView.findViewById(R.id.et_project_title);
            organization = itemView.findViewById(R.id.et_organization);
            startDate = itemView.findViewById(R.id.et_start_date);
            endDate = itemView.findViewById(R.id.et_end_date);
            projectDesc = itemView.findViewById(R.id.et_project_description);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }




}
