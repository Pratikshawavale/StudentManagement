package com.example.pratiksha.student_management.Fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pratiksha.student_management.R;
import com.example.pratiksha.student_management.db.DBhelper;
import com.example.pratiksha.student_management.db.Student;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {
    Button btnSearch,btnUpdate;
    EditText edtName,edtAddress,edtPhone,edtBranch,edtCourse;
    int id = -1;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        edtName = view.findViewById(R.id.edtName);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtBranch = view.findViewById(R.id.edtBranch);
        edtCourse = view.findViewById(R.id.edtCourse);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getActivity(),"Please enter name to search",Toast.LENGTH_SHORT).show();
                    return;

                }

                DBhelper dBhelper = new DBhelper(getActivity());

                Student student = dBhelper.getStudent(name);

                if(student==null)
                {
                    Toast.makeText(getActivity(),"Employee not found",Toast.LENGTH_SHORT).show();
                }
                else{
                    edtAddress.setText(student.getAddress());
                    edtPhone.setText(student.getPhone());
                    edtBranch.setText(student.getBranch());
                    edtCourse.setText(student.getCourse());
                    id = student.getId();

                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =edtName.getText().toString().trim();
                String address =edtAddress.getText().toString().trim();
                String phone =edtPhone.getText().toString().trim();
                String branch =edtBranch.getText().toString().trim();
                String course =edtCourse.getText().toString().trim();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(branch) || TextUtils.isEmpty(course)){
                    Toast.makeText(getActivity(),"Please enter all required fields",Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = new Student(name,address,phone,branch,course);

                DBhelper dBhelper = new DBhelper(getActivity());

                boolean isUpdated = dBhelper.updateStudent(student,id);

                if(isUpdated){
                    Toast.makeText(getActivity(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

}
