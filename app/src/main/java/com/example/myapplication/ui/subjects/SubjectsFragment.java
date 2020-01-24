package com.example.myapplication.ui.subjects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class SubjectsFragment extends Fragment {

    private SubjectsViewModel subjectsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        subjectsViewModel =
                ViewModelProviders.of(this).get(SubjectsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_subjects, container, false);
        final TextView textView = root.findViewById(R.id.text_subjects);
        subjectsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}