package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService
{

    @Autowired
    AuthorRepository authorRepository;    // to fetch author entity in bookService


    public String addBook(Book book)
    {
        //I want to get the AuthorEntity ???
        int authorId = book.getAuthor().getId();

//        try
//        {
            Author author = authorRepository.findById(authorId).get();
            // .get() method throw NoSuchElementException
//        }
//        catch(NoSuchElementException e)
//        {
//            return "Author is not present in Database";
//        }

        //Basic attributes are already set from postman

        //Setting the foreign key attr in the child class :
        book.setAuthor(author);

        //We need to also update the listOfBooks written in the parent class
        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);

        //Now the book is to be saved, but also author is alsoooo to be saved.

        //Why do we need to again save (updating) the author ?? bcz
        //bcz the author Entity has been updated....we need to resave/update it.
        authorRepository.save(author);   //Data was modified

        //.save function works both as save function and as update function


        //bookRepo.save is not required : bcz it will be autocalled by cascading
        //effect   otherwise duplicate books will be saved

        return "Book Added successfully";
    }
}
