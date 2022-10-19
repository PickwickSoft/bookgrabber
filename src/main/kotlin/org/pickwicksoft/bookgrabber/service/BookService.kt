package org.pickwicksoft.bookgrabber.service

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject
import org.pickwicksoft.bookgrabber.model.Book
import java.io.ByteArrayOutputStream
import java.net.URL
import java.util.*


class BookService {

    private var gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    fun getBookByISBN(isbn: String): Optional<Book> {
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

        return wrapOrEmpty(book)
    }

    private fun getJSONBookFromOpenLibrary(isbn: String): JSONObject? {
        return "https://openlibrary.org/api/books?bibkeys=ISBN:$isbn&jscmd=data&format=json"
            .httpGet()
            .responseJson().third.component1()?.obj()
    }

    private fun parseBookFromJSON(bookJSON: JSONObject?): Book? {
        return gson.fromJson(bookJSON.toString(), Book::class.java)
    }

    // Download image from url and return it as byte array
    fun getCoverFromURL(url: String): Optional<ByteArray> {
        try {
            val imageUrl = URL(url)
            val urlConnection = imageUrl.openConnection()
            val inputStream = urlConnection.getInputStream()
            val outputStream = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var read = 0
            while (inputStream.read(buffer, 0, buffer.size).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
            }
            outputStream.flush()
            return wrapOrEmpty(outputStream.toByteArray())
        } catch (_: Exception) {
        }
        return wrapOrEmpty(null)
    }

    private fun <T : Any> wrapOrEmpty(obj: T?): Optional<T> {
        if (obj != null) return Optional.of(obj)
        return Optional.empty()
    }
}
