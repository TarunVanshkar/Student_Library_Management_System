package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService
{

    @Autowired
    TransactionRepository transactionRepository;


    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;


    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception
    {
        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        //Get the Book Entity and Card Entity ??? Why do we need this
        //We are getting this bcz we want to set the Transaction attributes...

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();

        //Final goal is to make a transaction Entity, set its attribute
        //and save it.
        // First make a Transaction entity
        Transactions transactions = new Transactions();

        // Now setting the attributes
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);  // If somehow code breaks then status will be PENDING
        //transactions.setTransactionId(UUID.randomUUID().toString());   //-> already assigned
        // Fine will bw calculated at time of returning the book not at the time of bookIssue


        //attribute left is success/Failure
        //Check for validations
        if(book==null || book.isIssued()==true)
        {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book is not available");
        }

        if(card==null || !card.getCardStatus().equals(CardStatus.ACTIVATED))
        {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card is not valid");
        }

        //We have reached a success case now
        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        // Set the attributes of book
        book.setIssued(true);
        //Between the book and transaction : bidirectional
        List<Transactions> listOfTransactionsForBook = book.getListOfTransaction();
        listOfTransactionsForBook.add(transactions);
        book.setListOfTransaction(listOfTransactionsForBook);

        //I need to make changes in the card
        //Book and the card
        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);
        card.setBooksIssued(issuedBooksForCard);

        //Between Card and the Transaction : bidirectional (parent class)
        List<Transactions> transactionsListForCard = card.getTransactionsLst();
        transactionsListForCard.add(transactions);
        card.setTransactionsLst(transactionsListForCard);

        //Save the parent object
        cardRepository.save(card);
        //automatically by cascading : book and transaction will be saved.

        return "Book issued successfully";
    }

    public String getTransactions(int bookId, int cardId)
    {
        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId, cardId);

        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;
    }
}
