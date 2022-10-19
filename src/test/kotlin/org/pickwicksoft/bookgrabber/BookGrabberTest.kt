package org.pickwicksoft.bookgrabber

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.jvm.optionals.getOrNull

@OptIn(ExperimentalStdlibApi::class)
internal class BookGrabberTest {

    private var bookGrabber: BookGrabber = BookGrabber()

    @Test
    fun `get books by valid ISBN not empty`() {
        val books = bookGrabber.getBookByISBN("9783257230451")
        assertThat(books).isPresent
        assertThat(books.getOrNull()).isNotNull
    }

    @Test
    fun `get book by valid ISBN contains correct information`() {
        val books = bookGrabber.getBookByISBN("9783257230451")
        assertThat(books.get().title).isEqualTo("Der Besuch der alten Dame")
    }

    @Test
    fun `get books by invalid ISBN empty`() {
        val books = bookGrabber.getBookByISBN("97832572304598112")
        assertThat(books).isEmpty
    }

    @Test
    fun `get cover by valid URL not empty`() {
        val cover = bookGrabber.getCoverFromURL("https://covers.openlibrary.org/b/id/8139780-M.jpg")
        assertThat(cover).isPresent
    }

    @Test
    fun `get cover by invalid URL empty`() {
        val cover = bookGrabber.getCoverFromURL("https://covers.openlibrary.org/b/id/8139780-M.jpg123")
        assertThat(cover).isEmpty
    }
}