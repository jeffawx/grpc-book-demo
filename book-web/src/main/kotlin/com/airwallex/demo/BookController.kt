package com.airwallex.demo

import com.airwallex.grpc.annotations.GrpcClient
import com.github.michaelbull.result.get
import demo.BookServiceRpc
import demo.listBookRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/books")
class BookController(
    @GrpcClient(channel = "book")
    private val bookService: BookServiceRpc
) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/")
    suspend fun list(): List<Book> {
        logger.info("sending 'list' to book-service")

        return bookService.list(listBookRequest { }).get()!!.let {
            logger.info("get 'list' response from book-service")

            it.booksList.map { book ->
                Book(book.id, book.title, book.author)
            }
        }
    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable("id") id: Long): Book {
        logger.info("sending 'findById' to book-service")

        return bookService.findById(id).get()!!.let {
            logger.info("get 'findById' response from book-service")

            Book(it.id, it.title, it.author)
        }
    }
}
