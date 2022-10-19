package org.pickwicksoft.bookgrabber.model

data class Book(
    val title: String,
    val subtitle: String,
    val description: String,
    val authors: List<Author>,
    val numberOfPages: String,
    val identifiers: Identifier,
    val publishers: List<Publisher>,
    val publishDate: String,
    val subjects: List<Subject>,
    val cover: Cover,
    val language: String,
)

data class Publisher(
    val name: String
)

data class Subject(
    val name: String,
    val url: String
)

data class Cover(
    val small: String,
    val medium: String,
    val large: String,
)

data class Author(
    val url: String,
    val name: String
)

data class Identifier(
    val isbn13: List<String>,
    val openLibrary: List<String>
)