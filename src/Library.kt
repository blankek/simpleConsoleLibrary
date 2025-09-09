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

    fun addReader(readerNameInput: String?){
        val readerName = readerNameInput?.trim()
        if(readerName.isNullOrEmpty()){
            println("Введите автора")
            return
        }
        readersList.add(Reader(nextReaderId++, readerName))
        println(readerName + " добавлен")
    }

    fun findBook(bookNameInput: String?){
        val bookName = bookNameInput?.trim()
        if(bookName.isNullOrEmpty()){
            println("Введите название книги")
            return
        }
        val result = booksList.filter{it.name.contains(bookName, ignoreCase = true)}
        if(result.isEmpty()){ println("Ничего не найдено"); return}
        println("$bookName найдена")
    }

    fun giveBook(bookIdInput: String?, readerIdInput: String?){
        val bookId = bookIdInput?.toIntOrNull()
        val readerId = readerIdInput?.toIntOrNull()

        if(bookId == null) { println("Неверный Id книги"); return}
        if(readerId == null) { println("Неверный Id читателя"); return}

        val bookName = booksList.find{ it.id == bookId}
        val readerName = readersList.find { it.id == readerId }

        if(bookName == null){ println("Книга не найдена"); return}
        if(readerName == null){ println("Читатель не найден"); return}

        if(borrowed.containsKey(bookId)){
            println("Книга уже выдана")
            return
        }

        borrowed[bookId] = readerId
        println("Книга ${bookName.name} выдана читателю ${readerName.name}")
    }

    fun returnBook(bookIdInput: String?){
        val bookId = bookIdInput?.toIntOrNull()
        if (bookId == null) { println("Неверный Id книги"); return}
        if (!borrowed.containsKey(bookId)){
            println("Эта книга не выдана")
            return
        }

        borrowed.remove(bookId)
        val name = booksList.find{ it.id == bookId }?. name ?: "неизвестна"
        println("книга $name возвращена в библиотеку")
    }

    fun showAllBooks(){
        if (booksList.isEmpty()) { println("библиотека пуста"); return}
        println("Все книги")
        for(book in booksList){
            val status = if(borrowed.containsKey(book.id)){
                val readerId = borrowed[book.id]
                val readerName = readersList.find { it.id == readerId }?.name?:"неизвестен"
                "выдана $readerName"
            }else{
                "В наличии"
            }
            println(" [${book.id}] ${book.name} - $status")
        }
    }

    fun showAllReader(){
        if (readersList.isEmpty()){ println("Читателей нет"); return}
        println("Читатели")
        for (reader in readersList){
            println(" [${reader.id}] ${reader.name}")
        }
    }

}


