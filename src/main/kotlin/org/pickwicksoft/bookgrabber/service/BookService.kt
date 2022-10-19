package org.pickwicksoft.bookgrabber.service

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject
import org.pickwicksoft.bookgrabber.model.Book
import java.util.*

class BookService {

    private var gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    fun getBooksByISBN(isbn: String): Optional<Book> {
        return getBookFromOpenLibrary(isbn)
    }

    private fun getBookFromOpenLibrary(isbn: String): Optional<Book> {
        var bookJSON: JSONObject? = getJSONBookFromOpenLibrary(isbn)

        try {
            bookJSON = bookJSON?.getJSONObject("ISBN:$isbn")
        } catch (e: JSONException) {
            return Optional.empty()
        }

        val book = parseBookFromJSON(bookJSON)

        if (book != null) return Optional.of(book)
        return Optional.empty()
    }

    private fun getJSONBookFromOpenLibrary(isbn: String): JSONObject? {
        return "https://openlibrary.org/api/books?bibkeys=ISBN:$isbn&jscmd=data&format=json"
            .httpGet()
            .responseJson().third.component1()?.obj()
    }

    private fun parseBookFromJSON(bookJSON: JSONObject?): Book? {
        return gson.fromJson(bookJSON.toString(), Book::class.java)
    }
}
