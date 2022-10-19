# BookGrabber
A Kotlin/Java library to get book information and covers from the internet.

## API
BookGrabber currently supports the following operations:

- Get book information by ISBN:

    `BookGrabber.getBookByISBN(String isbn)`

- Get book cover from URL:

    `BookGrabber.getCoverFromURL(String url)`

## Java Example

```java
import org.pickwicksoft.bookgrabber.BookGrabber;
import org.pickwicksoft.bookgrabber.model.Book;

// Get book information by ISBN
// Instantiate BookGrabber
BookGrabber bookGrabber = new BookGrabber();

// Get book information by ISBN
Optional<Book> book = bookGrabber.getBookByISBN("978-3-257-23045-1");

// Check if book is present in the Optional
if (book.isPresent()) {
  System.out.println(book);
  
  // Get book cover from book cover URL
  Optional<byte[]> cover = bookGrabber.getCoverFromURL(book.get()
        .getCover().getLarge());
}
```

## Kotlin Example

```kotlin
import org.pickwicksoft.bookgrabber.BookGrabber

val grabber = BookGrabber()
val book = grabber.getBookByISBN("978-3-257-23045-1")
if (book.isPresent) {
    println(book)
    val cover = grabber.getCoverFromURL(book.get().cover.large)
}
```