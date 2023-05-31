package com.example.noteapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.noteapp2.database.Article;
import com.example.noteapp2.database.ArticleDB;

import java.util.List;

public class ArticleActivity extends AppCompatActivity{

    private Article targetArticle;
    private int articleId;
    private Fragment articleFragment;
    private Fragment articleEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Toolbar articleToolbar = (Toolbar) findViewById(R.id.id_article_toolbar);
        setSupportActionBar(articleToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_backspace);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            articleId = extras.getInt("id");
        }

        loadArticle(articleId);

        articleEditFragment = new ArticleEditFragment(targetArticle.title, targetArticle.subtitle, targetArticle.articleText);
        articleFragment = new ArticleFragment(targetArticle.title, targetArticle.subtitle, targetArticle.articleText);

//        title = fr.findViewById(R.id.article_title);
//        subtitle = fr.findViewById(R.id.article_subtitle);
//        articleText = fr.findViewById(R.id.articleText);
//

        showFragment(articleFragment);
    }

    private void loadArticle(int articleId){
        List<Article> list = findArticle(articleId);
        if(!list.isEmpty()){
            targetArticle = list.get(0);
        }
    }
    private List<Article> findArticle(int position){
        ArticleDB db = ArticleDB.getDbInstance(this.getApplicationContext());
        List<Article> target = db.articleDao().findById(position);
        return target;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.id_edit_tool){
            showFragment(articleEditFragment);
        }
        if(id == R.id.id_save_tool){
//            Log.d("CREATION", "View created " + articleEditFragment.getView());
//            System.out.print("View created");
            updateArticle();
            showFragment(articleFragment);
        }
        return true;
    }
    private void updateArticle(){
        EditText newTitle = articleEditFragment.getView().findViewById(R.id.edit_title);
        EditText newSubtitle = articleEditFragment.getView().findViewById(R.id.edit_subtitle);
        EditText newArticleText = articleEditFragment.getView().findViewById(R.id.edit_articleText);
        Article updatedArticle = new Article();
        updatedArticle.id = targetArticle.id;
        updatedArticle.title = newTitle.getText().toString();
        updatedArticle.subtitle = newSubtitle.getText().toString();
        updatedArticle.articleText = newArticleText.getText().toString();
        Log.d("CREATION", "Id:  " + updatedArticle.id);
        Log.d("CREATION", "title:  " + newTitle);
        Log.d("CREATION", "subtitle:  " + newSubtitle);
        Log.d("CREATION", "text:  " + newArticleText);
        ArticleDB db = ArticleDB.getDbInstance(this.getApplicationContext());
        db.articleDao().updateArticle(updatedArticle);
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_article_frame, fragment);
        fragmentTransaction.commit();
//        View fr = fragment.getView();
//        Log.d("CREATION", "View created " + fr);
//        System.out.print("View created");
    }
}