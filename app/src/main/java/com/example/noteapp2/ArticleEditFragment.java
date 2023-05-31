package com.example.noteapp2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ArticleEditFragment extends Fragment {

    private String title;
    private String subtitle;
    private String articleText;
    private FragmentDataListener fragmentDataListener;
    public ArticleEditFragment(String title, String subtitle, String articleText) {
        this.title = title;
        this.subtitle = subtitle;
        this.articleText = articleText;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_edit, container, false);

        TextView tvTitle = view.findViewById(R.id.edit_title);
        TextView tvSubtitle = view.findViewById(R.id.edit_subtitle);
        TextView tvArticleText = view.findViewById(R.id.edit_articleText);

        tvTitle.setText(title);
        tvSubtitle.setText(subtitle);
        tvArticleText.setText(articleText);

        return view;
    }
    public interface FragmentDataListener {
        void onDataReceived(String data);
    }
    public void setFragmentDataListener(FragmentDataListener listener) {
        this.fragmentDataListener = listener;
    }
    private void sendDataToActivity(String data) {
        if (fragmentDataListener != null) {
            fragmentDataListener.onDataReceived(data);
        }
    }
}