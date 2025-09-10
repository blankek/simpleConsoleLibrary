class Library {
    private var booksList: MutableList<Book> = mutableListOf()
    private val readersList: MutableList<Reader> = mutableListOf()

    private val borrowed = mutableMapOf<Int, Int>()

    private var nextBookId = 1
    private var nextReaderId = 1

    fun addBook(bookNameInput: String?){
        val bookName = bookNameInput?.trim()
        if (bookName.isNullOrEmpty()){
            println("Введите название книги")
            return
        }
        booksList.add(Book(nextBookId++, bookName))
        println("$bookName добавлена")
    }

    fun addReader(readerNameInput: String?){
        val readerName = readerNameInput?.trim()
        if(readerName.isNullOrEmpty()){
            println("Введите автора")
            return
        }
        readersList.add(Reader(nextReaderId++, readerName))
        println("$readerName добавлен")
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

    fun showBooksAndReaders(){
        val bookLines = booksList.map { book ->
            val status = borrowed[book.id]?.let { readerId ->
                val readerName = readersList.find { it.id == readerId }?.name ?: "неизвестен"
                "выдана ($readerName, id=$readerId)"
            } ?: "в наличии"
            "[${book.id}] ${book.name} — $status"
        }

        val readerLines = readersList.map { reader -> "[${reader.id}] ${reader.name}" }

        val leftWidth = (bookLines.maxOfOrNull { it.length } ?: 0) + 2
        val sep = " | "

        val rows = maxOf(bookLines.size, readerLines.size)

        val leftHeader = "Книги"
        val rightHeader = "Читатели"
        println(leftHeader.padEnd(leftWidth) + sep + rightHeader)

        val totalRightWidth = rightHeader.length
        println("-".repeat(leftWidth) + "-".repeat(sep.length) + "-".repeat(totalRightWidth))

        for (i in 0 until rows) {
            val left = if (i < bookLines.size) {
                val s = bookLines[i]
                if (s.length > leftWidth - 1) s.substring(0, leftWidth - 4) + "..." else s
            } else ""
            val right = if (i < readerLines.size) readerLines[i] else ""
            println(left.padEnd(leftWidth) + sep + right)
        }
        println("Нажмите Enter для продолжения...")
        readLine()
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
        println("Нажмите Enter для продолжения...")
        readLine()
    }

    fun showAllReader(){
        if (readersList.isEmpty()){ println("Читателей нет"); return}
        println("Читатели")
        for (reader in readersList){
            println(" [${reader.id}] ${reader.name}")
        }
        println("Нажмите Enter для продолжения...")
        readLine()
    }

}


