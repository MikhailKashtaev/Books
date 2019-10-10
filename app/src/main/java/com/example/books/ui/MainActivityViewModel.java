package com.example.books.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.books.model.Book;
import com.example.books.repository.BooksRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Book>> books;
    private MutableLiveData<String> error;

    public MainActivityViewModel() {
        books = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public MutableLiveData<List<Book>> getBooksLiveData() {
        return books;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void requestBooks() {
        BooksRepository.getInstance().getBooks(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    books.postValue(response.body());
                } else {
                    error.postValue(response.errorBody() == null ? "Unknown Error" : response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                error.postValue(t.getMessage());
            }
        });
    }

}
