package com.example.books.repository;

import com.example.books.model.Book;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BooksRepository {
    private static BooksRepository instance;

    private BooksAPI booksService;

    private BooksRepository() {
        String baseUrl = "https://de-coding-test.s3.amazonaws.com";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        booksService = retrofit.create(BooksAPI.class);
    }

    public static BooksRepository getInstance() {
        if (instance == null) {
            instance = new BooksRepository();
        }
        return instance;
    }

    public void getBooks(Callback<List<Book>> callback) {
        booksService.getBooks().enqueue(callback);
    }

}
