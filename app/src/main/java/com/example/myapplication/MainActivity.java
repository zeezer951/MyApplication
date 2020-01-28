package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText etItem = findViewById(R.id.etItem);
    RecyclerView rvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItem = findViewById(R.id.rvItem);



        items = new ArrayList<>();
        items.add("buy milk");
        items.add("get pineapple");
        items.add("Workout");




        final itemsAdapter ItemsAdapter = new itemsAdapter(items);
        rvItem.setAdapter(ItemsAdapter);
        rvItem.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etItem.getText().toString();

                items.add(todoItem);

                ItemsAdapter.notifyItemInserted(items.size() - 1);
                etItem.setText("");
                Toast.makeText(getApplicationContext(),"item was added", Toast.LENGTH_SHORT).show();
            }
        });



    }


}

