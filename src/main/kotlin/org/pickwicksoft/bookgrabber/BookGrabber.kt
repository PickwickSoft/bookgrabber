package org.pickwicksoft.bookgrabber

import org.pickwicksoft.bookgrabber.model.Book
import org.pickwicksoft.bookgrabber.service.BookService
import java.util.*

class BookGrabber {

    private val bookService: BookService = BookService()

    fun getBookByISBN(isbn: String): Optional<Book> {
        return bookService.getBookByISBN(isbn)
    }

    fun getCoverFromURL(url: String): Optional<ByteArray> {
        return bookService.getCoverFromURL(url)
    }
}