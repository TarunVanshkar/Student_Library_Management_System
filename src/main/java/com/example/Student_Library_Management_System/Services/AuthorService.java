package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService
{

    @Autowired
    AuthorRepository authorRepository;


    // Use of Object
//    public String createAuthor(Author author)
//    {
//        authorRepository.save(author);
//        return "Author added successfully";
//    }

    // Use of DTO
    public String createAuthor(AuthorEntryDto authorEntryDto)
    {

        //Important step is : in the params : the object i
        //of type DTO but the repository interacts only with entities

        //Solution : Convert authorEntryDto ---> Author

        //Created an object of type Author
        Author author = new Author();

        //we are setting its attribute so that we can save
        //correct values in the data base.
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        authorRepository.save(author);   // Because Repository layer only interact with object , not with DTOs
        return "Author added successfully";
    }

//    public Author getAuthor(int authorId)
//    {
//        return authorRepository.findById(authorId).get();
//    }
    // The above API will stuck in infinite recursion due to a list of book which author is having
    // To resolve it, we will return a response Dto by below Api

    public AuthorResponseDto AuthorResponseDto(int authorId)
    {
        Author author = authorRepository.findById(authorId).get();

        // Now create authorResponseDto
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();

        // Now set its attributes
        // Convert List<Book> ----> List<BookResponseDto>
        List<Book> booksList = author.getBooksWritten();

        List<BookResponseDto> bookWritten = new ArrayList<>();
        for(Book b : booksList)
        {
            BookResponseDto bookResponseDto = new BookResponseDto();
            // Set its attributes
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setName(b.getName());

            bookWritten.add(bookResponseDto);
        }

        // Now set attributes for AuthorResponseDto
        authorResponseDto.setBooksWritten(bookWritten);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());
        authorResponseDto.setCountry(author.getCountry());
        return authorResponseDto;

    }
}
