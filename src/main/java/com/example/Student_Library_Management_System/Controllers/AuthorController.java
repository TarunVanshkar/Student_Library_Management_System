package com.example.Student_Library_Management_System.Controllers;


import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("author")
public class AuthorController
{
    // "/" is optional : you want to write you can
    // otherwise not required

    @Autowired
    AuthorService authorService;


//    @PostMapping("/add")
//    public String addAuthor(@RequestBody Author author)
//    {
//        return authorService.createAuthor(author);
//    }

    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorEntryDto authorEntryDto)
    {
        return authorService.createAuthor(authorEntryDto);
    }


//    @GetMapping("/getAuthor")
//    public Author getAuthor(@RequestParam("authorId") int id)
//    {
//        return authorService.getAuthor(id);
//    }
    // The above API will stuck in infinite recursion due to a list of book which author is having
    // Its solution is below

    @GetMapping("/getAuthor")
    public AuthorResponseDto AuthorResponseDto(@RequestParam("authorId") int id)
    {
        return authorService.AuthorResponseDto(id);
    }
}
