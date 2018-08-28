package com.example.pratiksha.student_management.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratiksha.student_management.R;

public class StudentViewHolder extends  RecyclerView.ViewHolder {
    TextView txtName,txtAddress,txtPhone,txtBranch,txtCourse;
    ImageView imgDelete;

    public StudentViewHolder(View itemView) {
        super(itemView);

        txtName = itemView.findViewById(R.id.txtName);
        txtAddress = itemView.findViewById(R.id.txtAddress);
        txtPhone = itemView.findViewById(R.id.txtPhone);
        txtBranch = itemView.findViewById(R.id.txtBranch);
        txtCourse = itemView.findViewById(R.id.txtCourse);
        imgDelete = itemView.findViewById(R.id.imgDelete);
    }
}
