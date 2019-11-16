package com.example.vocabularynote;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class addWordsFragment extends Fragment {
    EditText editTextEng, editTextCh;
    Button buttonSubmit;
    WordViewModel wordViewModel;

    public addWordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_words, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        wordViewModel = ViewModelProviders.of(activity).get(WordViewModel.class);
        editTextEng = activity.findViewById(R.id.editTextEng);
        editTextCh = activity.findViewById(R.id.editTextCh);
        buttonSubmit = activity.findViewById(R.id.buttonSubmit);
        buttonSubmit.setEnabled(false);
        editTextEng.requestFocus();
        final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editTextEng, 0);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String eng = editTextEng.getText().toString().trim();
                String ch = editTextCh.getText().toString().trim();
                buttonSubmit.setEnabled(!eng.isEmpty() && !ch.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editTextEng.addTextChangedListener(watcher);
        editTextCh.addTextChangedListener(watcher);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eng = editTextEng.getText().toString().trim();
                String ch = editTextCh.getText().toString().trim();
                Word word = new Word(eng, ch);
                wordViewModel.insertWord(word);
                NavController controller = Navigation.findNavController(v);
                controller.navigateUp();
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            }
        });
    }
}
