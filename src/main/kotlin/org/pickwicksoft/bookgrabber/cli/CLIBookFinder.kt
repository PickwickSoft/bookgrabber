package org.pickwicksoft.bookgrabber.cli

import org.pickwicksoft.bookgrabber.BookGrabber
import java.io.File

fun main(args: Array<String>) {
    val grabber = BookGrabber()
    val book = grabber.getBookByISBN(args[0])
    println(book)
    println(book.get().cover.large)
    val cover = grabber.getCoverFromURL(book.get().cover.large)
    File("cover.jpg").writeBytes(cover.get())
}