package com.resumebuilder.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumebuilder.app.R;
import com.resumebuilder.app.itemClasses.Skill;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder>{

    private final List<Skill> skillList;


    public SkillAdapter(List<Skill> skillList) {
        this.skillList = skillList;
    }

    @NonNull
    @Override
    public SkillAdapter.SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_skill, parent, false);
        return new SkillAdapter.SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillAdapter.SkillViewHolder holder, int position) {
        Skill skill = skillList.get(position);

        holder.nameOfSkill.setText(skill.getNameOfSkill());

        holder.btnRemove.setOnClickListener(v -> {
            skillList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, skillList.size());
        });

    }


    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        EditText nameOfSkill;
        Button btnRemove;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfSkill = itemView.findViewById(R.id.et_skill_name);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }




}
