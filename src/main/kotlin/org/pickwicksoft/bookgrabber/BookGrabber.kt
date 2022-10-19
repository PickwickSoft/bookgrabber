package org.pickwicksoft.bookgrabber

import org.pickwicksoft.bookgrabber.model.Book
import org.pickwicksoft.bookgrabber.service.BookService
import java.util.*

class BookGrabber {

    private val bookService: BookService = BookService()

    fun getBooksByISBN(isbn: String): Optional<Book> {
        return bookService.getBooksByISBN(isbn)
    }
}