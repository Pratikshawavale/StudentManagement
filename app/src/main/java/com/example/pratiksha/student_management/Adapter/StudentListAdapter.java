package com.example.pratiksha.student_management.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratiksha.student_management.R;
import com.example.pratiksha.student_management.db.DBhelper;
import com.example.pratiksha.student_management.db.Student;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentViewHolder>{

    List<Student> students;
    DBhelper dBhelper;

    public StudentListAdapter(List<Student> students, DBhelper dBhelper){
        this.students =students;
        this.dBhelper = dBhelper;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_list,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        final Student student = students.get(position);
        holder.txtName.setText("Name : "+ student.getName());
        holder.txtAddress.setText("Address : "+ student.getAddress());
        holder.txtPhone.setText("Phone : "+student.getPhone());
        holder.txtBranch.setText("Branch : "+ student.getBranch());
        holder.txtCourse.setText("Course : "+ student.getCourse());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDeleted = dBhelper.deleteStudent(student.getId());
                if(isDeleted){
                    students.remove(student);
                    notifyDataSetChanged();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
