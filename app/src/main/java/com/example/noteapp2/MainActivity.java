package com.example.noteapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.noteapp2.database.Article;
import com.example.noteapp2.database.ArticleDB;
import com.example.noteapp2.database.ArticleListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IAddArticleDialog{

    //private static final String DB_URL = "http://192.168.0.102/article/register.php";
//    private ListView listview;
//    private List<String> list;
//    private ArrayAdapter<String> arrayAdapter;
    private ArticleListAdapter articleListAdapter;

    //private SQLHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.id_main_toolbar);
        setSupportActionBar(mainToolbar);

        //db = new SQLHandler(getApplicationContext());

        //listview = (ListView) findViewById(R.id.id_listview);

        //list = new ArrayList<>();
        //arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_layout, list);

        //listview.setAdapter(arrayAdapter);
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                openArticleActivity();
//            }
//        });

        initRecyclerView();

        loadArticleList();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        articleListAdapter = new ArticleListAdapter(this);
        recyclerView.setAdapter(articleListAdapter);
        articleListAdapter.setOnItemClickListener(new ArticleListAdapter.IOnItemClick() {
            @Override
            public void onItemClick(int position) {
                openArticleActivity(position);
            }
        });
    }

    private void loadArticleList() {
        ArticleDB db = ArticleDB.getDbInstance(this.getApplicationContext());
        List<Article> articleList = db.articleDao().getAllUsers();
        articleListAdapter.setArticleList(articleList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadArticleList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadArticleList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void openArticleActivity(int id){
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("id", id + 1);
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
        dialog.show(getSupportFragmentManager(),"Add new article");
    }

    //add new list item
    @Override
    public void createNewArticle(String title, String subtitle) {

        ArticleDB db  = ArticleDB.getDbInstance(this.getApplicationContext());

        Article article = new Article();
        article.title = title;
        article.subtitle = subtitle;

        //list.add(title);
        //arrayAdapter.notifyDataSetChanged();
        db.articleDao().insertUser(article);
        loadArticleList();
    }
}