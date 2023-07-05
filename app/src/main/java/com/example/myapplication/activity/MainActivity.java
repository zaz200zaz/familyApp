package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextAge;
    private ListView listViewFamilyTree;
    private ArrayList<String> familyMembers;
    private ArrayAdapter<String> arrayAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        listViewFamilyTree = findViewById(R.id.listViewFamilyTree);

        familyMembers = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, familyMembers);
        listViewFamilyTree.setAdapter(arrayAdapter);
        databaseHelper = new DatabaseHelper(this);
    }

    public void addMember(View view) {
        String name = editTextName.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());

        String member = "Name: " + name + ", Age: " + age;
        familyMembers.add(member);
        arrayAdapter.notifyDataSetChanged();

        editTextName.setText("");
        editTextAge.setText("");

        databaseHelper.insertMember(name, age);

    }
    public void editMember(View view) {
        String name = editTextName.getText().toString();
        int age = Integer.parseInt(editTextAge.getText().toString());

        // Sửa đổi thành viên trong cơ sở dữ liệu SQLite
        databaseHelper.updateMember(name, age);

        // ...

    }

    public void deleteMember(View view) {
        String name = editTextName.getText().toString();

        // Xóa thành viên khỏi cơ sở dữ liệu SQLite
        databaseHelper.deleteMember(name);

        // ...

    }
}
