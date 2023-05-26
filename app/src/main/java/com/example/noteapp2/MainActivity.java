package com.example.noteapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IAddArticleDialog{

    private ListView listview;
    private List<String> list;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        setSupportActionBar(mainToolbar);

        listview = (ListView) findViewById(R.id.id_listview);

        list = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_layout, list);

        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openArticleActivity();
            }
        });
    }

    public void openArticleActivity(){
        Intent intent = new Intent(this, ArticleActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.id_add_tool){
            showAddArticleDialog();
        }
        return true;
    }

    private void showAddArticleDialog() {
        AddArticleDialog dialog = new AddArticleDialog();
//        dialog.setContentView(R.layout.dialog_layout);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.setCancelable(true);
//
//        editTitle = findViewById(R.id.id_edit_title);
//        editSubtitle = findViewById(R.id.id_edit_subtitle);
//        addButton = findViewById(R.id.id_add_button);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addItemToList(v);
//            }
//        });
        dialog.show(getSupportFragmentManager(),"Add new article");
    }

    @Override
    public void applyEditText(String title, String subTitle) {
        list.add(title);
        arrayAdapter.notifyDataSetChanged();
    }
}