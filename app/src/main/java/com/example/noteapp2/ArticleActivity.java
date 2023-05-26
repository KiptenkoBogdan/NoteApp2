package com.example.noteapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ArticleActivity extends AppCompatActivity {

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
        articleEditFragment = new ArticleEditFragment();
        articleFragment = new ArticleFragment();
        showFragment(articleFragment);
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
            showFragment(new ArticleEditFragment());
        }
        if(id == R.id.id_save_tool){
            showFragment(new ArticleFragment());
        }
        return true;
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_article_frame, fragment);
        fragmentTransaction.commit();
    }
}