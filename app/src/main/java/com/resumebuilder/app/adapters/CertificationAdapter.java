package com.resumebuilder.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.resumebuilder.app.R;
import com.resumebuilder.app.itemClasses.Certification;

import java.util.List;

public class CertificationAdapter extends RecyclerView.Adapter<CertificationAdapter.CertificationViewHolder>{

    private final List<Certification> certificationList;


    public CertificationAdapter(List<Certification> certificationList) {
        this.certificationList = certificationList;
    }

    @NonNull
    @Override
    public CertificationAdapter.CertificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_certifications, parent, false);
        return new CertificationAdapter.CertificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CertificationAdapter.CertificationViewHolder holder, int position) {
        Certification certification = certificationList.get(position);

        holder.nameOfCertification.setText(certification.getNameOfCertification());
        holder.nameOfOrganization.setText(certification.getNameOfOrganization());
        holder.issuedDate.setText(certification.getIssuedDate());
        holder.link.setText(certification.getLink());

        holder.btnRemove.setOnClickListener(v -> {
            certificationList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, certificationList.size());
        });

    }


    @Override
    public int getItemCount() {
        return certificationList.size();
    }

    public void updateCertificationListFromInputs() {
        for (int i = 0; i < getItemCount(); i++) {
            CertificationAdapter.CertificationViewHolder holder = (CertificationAdapter.CertificationViewHolder) bindingRecyclerView.findViewHolderForAdapterPosition(i);
            if (holder != null) {
                String nameOfCertification = holder.nameOfCertification.getText().toString().trim();
                String nameOfOrganization = holder.nameOfOrganization.getText().toString().trim();
                String issuedDate = holder.issuedDate.getText().toString().trim();
                String link = holder.link.getText().toString().trim();

                certificationList.get(i).setNameOfCertification(nameOfCertification);
                certificationList.get(i).setNameOfOrganization(nameOfOrganization);
                certificationList.get(i).setLink(link);
                certificationList.get(i).setIssuedDate(issuedDate);
            }
        }
    }

    // 📌 Needs to be set from the Fragment to access RecyclerView reference
    private RecyclerView bindingRecyclerView;
    public void setRecyclerView(RecyclerView recyclerView) {
        this.bindingRecyclerView = recyclerView;
    }

    public static class CertificationViewHolder extends RecyclerView.ViewHolder {
        EditText nameOfCertification, nameOfOrganization, issuedDate, link;
        Button btnRemove;

        public CertificationViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfCertification = itemView.findViewById(R.id.et_cert_name);
            nameOfOrganization = itemView.findViewById(R.id.et_cert_organization);
            issuedDate = itemView.findViewById(R.id.et_issued_date);
            link = itemView.findViewById(R.id.et_link);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
