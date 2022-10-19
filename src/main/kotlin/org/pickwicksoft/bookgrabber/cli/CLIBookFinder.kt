package org.pickwicksoft.bookgrabber.cli

import org.pickwicksoft.bookgrabber.BookGrabber

fun main(args: Array<String>) {
    val grabber = BookGrabber()
    val book = grabber.getBooksByISBN(args[0])
    println(book)
}