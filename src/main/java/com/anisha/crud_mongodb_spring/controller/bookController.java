package com.anisha.crud_mongodb_spring.controller;

import com.anisha.crud_mongodb_spring.model.book_data;
import com.anisha.crud_mongodb_spring.repository.bookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081") //configuring allowed origins
@RestController
//annotation is used to define a controller and to indicate that the return value of the methods
// should be bound to the web response body.
@RequestMapping("/api") //declares that all Apis’ url in the controller will start with /api.
public class bookController {

    @Autowired //to inject bookRepo bean to local variable.
    private bookRepo bookRepo;

    @GetMapping("/book") //returns all the books if there is 'title' parameter
    public ResponseEntity<List<book_data>> getAllBookById(@RequestParam(required = false) String title){

        try {
            List<book_data> book_data = new ArrayList<book_data>();

            if (title == null)
                book_data.addAll(bookRepo.findAll());
            else
                book_data.addAll(bookRepo.findByTitleContaining(title));

            if (book_data.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(book_data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/book/{id}") //return Books with the given id
    public ResponseEntity<book_data> getBookById(@PathVariable("id") String id){
        Optional<book_data> bookData = bookRepo.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/book") //for handling POST HTTP requests.  A new Book will be created by MongoRepository.save() method.
    public ResponseEntity<book_data> createBookById(@RequestBody book_data bookData) {

        try {
            book_data book_data = bookRepo.save(new book_data(bookData.getTitle(), bookData.getDescription(), false));
            return new ResponseEntity<>(book_data,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/book/{id}")
    //receives id and a Book payload.
    //– from the id, we get the Book from database using findById() method.
    //– then we use the payload and save() method for updating the Tutorial.
    public ResponseEntity<book_data> updateBookById(@PathVariable("id") String id, @RequestBody book_data bookData) {
        Optional<book_data> book_data = bookRepo.findById(id); //gets the book from the repo by finding via

        if (book_data.isPresent()) {
            book_data data = book_data.get();
            data.setTitle(bookData.getTitle());
            data.setDescription(bookData.getDescription());
            data.setPublished(bookData.isPublished());
            return new ResponseEntity<>(bookRepo.save(data), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/book/{id}")
    //delete a book with given id
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") String id) {
        try {
            bookRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/book")
    //remove all the books from the database
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            bookRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book/published") //return books if published == true
    public ResponseEntity<List<book_data>> findByPublished() {
        //List<book_data> book_data = bookRepo.findByPublished(true);
        try {
            List<book_data> book_data = bookRepo.findByPublished(true);

            if (book_data.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/book/{title}")
//    public ResponseEntity<List<book_data>> findByTitle(@PathVariable("title") String title) {
//        try {
//            List<book_data> bookDataList = bookRepo.findByTitleContaining(title);
//
//            if (bookDataList.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(bookDataList, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
