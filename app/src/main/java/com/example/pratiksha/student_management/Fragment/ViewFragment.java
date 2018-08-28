package com.example.pratiksha.student_management.Fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pratiksha.student_management.Adapter.StudentListAdapter;
import com.example.pratiksha.student_management.R;
import com.example.pratiksha.student_management.db.DBhelper;
import com.example.pratiksha.student_management.db.Student;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {
    RecyclerView rvUsers;

    public ViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view,container,false);
        rvUsers = view.findViewById(R.id.rvUsers);

        rvUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        DBhelper dBhelper = new DBhelper(getActivity());

        List<Student> students = dBhelper.getAllStudent();
        StudentListAdapter adapter = new StudentListAdapter(students,dBhelper);

        rvUsers.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }

}
