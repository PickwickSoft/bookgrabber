package org.pickwicksoft.bookgrabber

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.jvm.optionals.getOrNull

internal class BookGrabberTest {

    private var bookGrabber: BookGrabber = BookGrabber()

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `get books by valid ISBN not empty`() {
        val books = bookGrabber.getBooksByISBN("9783257230451")
        assertThat(books).isPresent
        assertThat(books.getOrNull()).isNotNull
    }

    @Test
    fun `get book by valid ISBN contains correct information`() {
        val books = bookGrabber.getBooksByISBN("9783257230451")
        assertThat(books.get().title).isEqualTo("Der Besuch der alten Dame")
    }

    @Test
    fun `get books by invalid ISBN empty`() {
        val books = bookGrabber.getBooksByISBN("97832572304598112")
        assertThat(books).isEmpty
    }
}