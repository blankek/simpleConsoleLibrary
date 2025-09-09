class Library {
    var booksList: MutableList<Book> = mutableListOf()
    val readersList: MutableList<Reader> = mutableListOf()

    val borrowed = mutableMapOf<Int, Int>()

    private var nextBookId = 1
    private var nextReaderId = 1

    fun addBook(book: String?){
        val bookName = book?.trim()
        if (bookName.isNullOrEmpty()){
            println("Введите название книги")
            return
        }
        booksList.add(Book(nextBookId++, bookName))
        println(bookName + " добавлена")
    }

    fun addReader(readerName: String?){
        val readerName = readerName?.trim()
        if(readerName.isNullOrEmpty()){
            println("Введите автора")
            return
        }
        readersList.add(Reader(nextReaderId++, readerName))
        println(readerName + " добавлен")
    }

    fun findBook(bookName: String?){
        val query = bookName?.trim()
        if(query.isNullOrEmpty()){
            println("Введите название книги")
            return
        }
        var result = booksList.filter{it.name.contains(query, ignoreCase = true)}
        if(result.isEmpty()){ println("Ничего не найдено"); return}
        for (book in booksList){
            val status = if(borrowed.containsKey(book.id)){
                val readerId = borrowed[book.id]
                val readerName = readersList.find{it.id == readerId}?.name ?: "неизвестен"
                "выдана читателю $readerName id=$readerId"
            }else{
                "в наличии"
            }
            println("[${book.id}] ${book.name} - $status")
        }
    }

    fun showAllBooks(){
        if (booksList.isNullOrEmpty()){
            println("Библиотека пуста")
        }
        println("Все книги: ")
        println(booksList.joinToString())
    }

}


