package com.example.books.repository;

import com.example.books.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksAPI {
    @GET("books.json")
    Call<List<Book>> getBooks();
}
