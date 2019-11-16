package com.example.vocabularynote;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordDao wordDao;
    private WordRepository wordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }

    void insertWord(Word... words) {
        wordRepository.insertWord(words);
    }

    void updateWord(Word... words) {
        wordRepository.updateWord(words);
    }

    void deleteWord(Word... words) {
        wordRepository.deleteWord(words);
    }

    void deleteAllWords() {
        wordRepository.deleteAllWords();
    }


}
