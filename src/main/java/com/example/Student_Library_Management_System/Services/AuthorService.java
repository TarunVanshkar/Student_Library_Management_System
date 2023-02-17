package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
