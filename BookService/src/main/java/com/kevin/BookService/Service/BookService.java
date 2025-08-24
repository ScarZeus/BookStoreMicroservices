package com.kevin.BookService.Service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kevin.BookService.Model.BookModel;
import com.kevin.BookService.Model.ImageModel;
import com.kevin.BookService.Repository.BookRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookDao;

    @Autowired
    private Cloudinary cloudinary;


    @Cacheable(value = "books")
    public List<BookModel> getAllBooks() {
        List<BookModel> books = bookDao.findAll();
        if (books == null || books.isEmpty()) {
            throw new RuntimeException("No Books Found");
        }
        return books;
    }


    @Cacheable(value = "booksByName", key = "#bookName + '_' + #isbnNO")
    public List<BookModel> getAllBooksByName(String bookName, String isbnNO) {
        List<BookModel> books = bookDao.findAllByTitleOrIsbn(bookName, isbnNO);
        if (books == null || books.isEmpty()) {
            throw new RuntimeException("No Books Found");
        }
        return books;
    }


    @CacheEvict(value = {"books", "booksByName"}, allEntries = true)
    public BookModel saveBook(BookModel book, MultipartFile[] images) {
        try {
            if (bookDao.existsByIsbn(book.getIsbn())) {
                throw new RuntimeException("Book Already Exists");
            }

            List<ImageModel> imageList = new ArrayList<>();

            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    Map uploadResult = cloudinary.uploader().upload(
                            file.getBytes(),
                            ObjectUtils.asMap("folder", "books/" + book.getIsbn())
                    );

                    String imageUrl = uploadResult.get("secure_url").toString();

                    ImageModel img = new ImageModel();
                    img.setUrl(imageUrl);
                    img.setBook(book);

                    imageList.add(img);
                }
            }

            book.setImages(imageList);
            return bookDao.save(book);

        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to Cloudinary", e);
        }
    }


    @CachePut(value = "books", key = "#book.id")
    public BookModel updateBook(BookModel book) {
        if (!bookDao.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException("Book not found for update");
        }
        return bookDao.save(book);
    }


    @Transactional
    @CacheEvict(value = {"books", "booksByName"}, allEntries = true)
    public void deleteBook(BookModel book) {
        if (bookDao.existsById(book.getId())) {
            try {
                if (book.getImages() != null) {
                    for (ImageModel image : book.getImages()) {
                        // only delete if publicId is present
                        if (image.getUrl() != null) {
                            cloudinary.uploader().destroy(image.getUrl(), ObjectUtils.emptyMap());
                        }
                    }
                }
                bookDao.delete(book);
            } catch (IOException e) {
                throw new RuntimeException("Failed to delete image(s) from Cloudinary", e);
            }
        }
    }


    @CacheEvict(value = {"books", "booksByName"}, allEntries = true)
    public void updateAllBooks(List<BookModel> books) {
        for (BookModel book : books) {
            if (book.isInStocks() && book.getStockCount() == 0) {
                book.setInStocks(false);
            }
            updateBook(book);
        }
    }
}
