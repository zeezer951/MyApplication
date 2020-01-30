package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import java.io.File;

import java.nio.charset.Charset;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItem;
    itemsAdapter ItemsAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItem = findViewById(R.id.rvItem);




        loadItems();

        itemsAdapter.OnLongClickListener onLongClickListener = new itemsAdapter.OnLongClickListener()
        {
            @Override
            public void onItemLongClicked(int position) {
                items.remove(position);

                ItemsAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(),"item was removed", Toast.LENGTH_SHORT).show();
            }

        };


        ItemsAdapter = new itemsAdapter(items, onLongClickListener);
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
                saveItems();
            }
        });



    }



        private File getDataFile(){
            return new File(getFilesDir(), "data.txt");


        }

    private void loadItems(){
        try{
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset());
        } catch (IOException e){
            Log.e("MainActivity", "error reading items", e);
            items.new ArrayList<>();
    }
    }


    private void saveItems() {
        try{
            FileUtils.writeLines(getDataFile(), items);
        } catch(IOException e) {

            Log.e("MainActivity", "Error writing items", e);
        }
    }

}

