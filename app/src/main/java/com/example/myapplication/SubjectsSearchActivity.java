package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class SubjectsSearchActivity extends AppCompatActivity {

    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_search);
        fillExampleList();
        setUpRecyclerView();
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("Artificial Intelligence and Expert Systems", "7 ECTS", "T:(P)10:30-11:40"));
        exampleList.add(new ExampleItem("Cloud Computing Systems", "3 ECTS", "M:(L)8:30-10:00 even weeks Th:(P)12:15-13:45"));
        exampleList.add(new ExampleItem("Computer Network Administration", "5 ECTS", "Th:(L)16:00-18:00"));
        exampleList.add(new ExampleItem("Computer Networks", "6 ECTS", "T:14:15-20:00"));
        exampleList.add(new ExampleItem("Intellectual Property Protection", "1 ECTS", "W:(L)10:00-12:00"));
        exampleList.add(new ExampleItem("Introduction to Information Technology", "2 ECTS", ":::::"));
        exampleList.add(new ExampleItem("Introduction to Internet of Things", "3 ECTS", "F:(P)10:15-11:45,(L)14:15-16:00 even weeks"));
        exampleList.add(new ExampleItem("Introduction to Mobile Systems", "3 ECTS", "W:(L)16:00-17:30 Th:(P)14:00-15:30"));
        exampleList.add(new ExampleItem("Software Engineering", "6 ECTS", "W:(P)08:30-10:00 Th:(L)10:30-12:00"));
        exampleList.add(new ExampleItem("Virtual Start-Up", "2 ECTS", "F:(L)14:00-16:00"));
        exampleList.add(new ExampleItem("Scripting Languages", "2 ECTS", "F:(L)14:00-16:00"));
        exampleList.add(new ExampleItem("Security of Information Systems", "4 ECTS", "M:(L)13:45-15:15 T:(P)8:30-10:00"));
        exampleList.add(new ExampleItem("Network Programming", "5 ECTS", ":::::"));
        exampleList.add(new ExampleItem("Image Processing", "4 ECTS", ":::::"));
        exampleList.add(new ExampleItem("Computer Aided Design", "3 ECTS", ":::::"));
        exampleList.add(new ExampleItem("Business English for Engineers 1", "2 ECTS", ":::::"));
        exampleList.add(new ExampleItem("Business English for Engineers 2", "2 ECTS", ":::::"));

    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
