package com.example.pratiksha.student_management.Fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class AddFragment extends Fragment {

    EditText edtName,edtAddress,edtPhone,edtBranch,edtCourse;
    Button btnSave;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        edtName = view.findViewById(R.id.edtName);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtBranch = view.findViewById(R.id.edtBranch);
        edtCourse = view.findViewById(R.id.edtCourse);
        btnSave = view.findViewById(R.id.btnSave);

         btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =edtName.getText().toString().trim();
                String address =edtAddress.getText().toString().trim();
                String phone =edtPhone.getText().toString().trim();
                String branch =edtBranch.getText().toString().trim();
                String course =edtCourse.getText().toString().trim();

                Student student = new Student(name,address,phone,branch,course);

                DBhelper dBhelper = new DBhelper(getActivity());
                boolean isAdded = dBhelper.addStudents(student);
                if(isAdded){
                    Toast.makeText(getActivity(),"Record Added",Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;

    }

}
