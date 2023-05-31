package com.example.noteapp2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArticleFragment extends Fragment {

    private String title;
    private String subtitle;
    private String articleText;

    public ArticleFragment(String title, String subtitle, String articleText) {
        this.title = title;
        this.subtitle = subtitle;
        this.articleText = articleText;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        TextView tvTitle = view.findViewById(R.id.article_title);
        TextView tvSubtitle = view.findViewById(R.id.article_subtitle);
        TextView tvArticleText = view.findViewById(R.id.articleText);

        tvTitle.setText(title);
        tvSubtitle.setText(subtitle);
        tvArticleText.setText(articleText);

//        Log.d("CREATION", "View created " + view);
//        System.out.print("View created");
        return view;
    }

//    private void updateText(String title, String subtitle, String articleText) {
//        this.title = title;
//        this.subtitle = subtitle;
//        this.articleText = articleText;
//    }
}