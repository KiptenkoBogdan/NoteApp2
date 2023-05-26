package com.example.noteapp2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddArticleDialog extends AppCompatDialogFragment {
    private EditText editTitle, editSubtitle;
    private IAddArticleDialog dialogInterface;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflator = getActivity().getLayoutInflater();

        View view = inflator.inflate(R.layout.dialog_layout, null);

        editTitle = view.findViewById(R.id.id_edit_title);
        editSubtitle = view.findViewById(R.id.id_edit_subtitle);

        builder.setView(view)
                .setTitle("Add new article")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialog, int which) {
                        String title = editTitle.getText().toString();
                        String subTitle = editSubtitle.getText().toString();
                        dialogInterface.applyEditText(title, subTitle);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dialogInterface = (IAddArticleDialog) context;
    }
}
