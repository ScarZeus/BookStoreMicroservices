package com.kevin.BookService.Service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kevin.BookService.Model.BookModel;
import com.kevin.BookService.Model.ImageModel;
import com.kevin.BookService.Repository.BookRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookDao;

    @Autowired
    private Cloudinary cloudinary;


    public List<BookModel> getAllBooks(){
        try{
            List<BookModel> books = bookDao.findAll();
            if (books.isEmpty() || books == null){
                throw new RuntimeException("No Books Found");
            }
            return books;
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    public List<BookModel> getAllBooksByName(String bookName,String ibsnNO){
        try {
            List<BookModel> books = bookDao.findAllByTitleOrIsbn(bookName,ibsnNO);
            if(books==null && books.isEmpty()){
                throw new RuntimeException("No Books Found");
            }
            return books;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public BookModel saveBook(BookModel book, MultipartFile[] images) {
        try {
            if (bookDao.existsByIsbn(book.getIsbn())) {
                throw new RuntimeException("Book Already Exists");
            }

            List<ImageModel> imageList = new ArrayList<>();

            // Upload each image to Cloudinary and create ImageModel
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                            "folder", "books/" + book.getIsbn() // optional folder structure
                    ));

                    String imageUrl = uploadResult.get("secure_url").toString();

                    ImageModel img = new ImageModel();
                    img.setUrl(imageUrl);
                    img.setBook(book); // important for bidirectional mapping

                    imageList.add(img);
                }
            }

            // Set images in BookModel
            book.setImages(imageList);

            return bookDao.save(book);

        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to Cloudinary", e);
        }
    }
    public void updateBook(BookModel book){
        if(!bookDao.existsByIsbn(book.getIsbn())){
            bookDao.save(book);
        }
    }



    @Transactional
    public void deleteBook(BookModel book) {
        if (bookDao.existsById(book.getId())) {
            try {
                // 1️⃣ Delete gallery images from Cloudinary
                if (book.getImages() != null) {
                    for (ImageModel image : book.getImages()) {
                        if (image.getPublicId() != null) {
                            cloudinary.uploader().destroy(image.getPublicId(), ObjectUtils.emptyMap());
                        }
                    }
                }

                // 3️⃣ Delete the book from DB
                bookDao.delete(book);

            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image(s) from Cloudinary", e);
            }
        }
    }


    public void updateAllBooks(List<BookModel> books) {
        try{
            for(BookModel book : books){
                if(book.isInStocks() && book.getStockCount()==0){
                    book.setInStocks(false);
                }
                updateBook(book);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
