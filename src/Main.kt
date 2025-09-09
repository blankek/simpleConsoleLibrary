fun main() {
    val library = Library()
    while(true){
        println("""
            Выберите действие:
            1) Добавить книгу
            2) Добавить автора
            3) Найти книгу
            4) Взять книгу
            5) Вернуть книгу
            6) Показать все книги
            7) Показать всех читателей
            0) Выход
        """.trimIndent())
        when(readLine()?.trim()){
            "1" -> {
                println("Введите название книги")
                library.addBook(readLine())
            }
            "2" -> {
                println("Введите имя читателя")
                library.addReader(readLine())
            }
            "3" -> {
                println("Введите название книги")
                library.findBook(readLine())
            }
            "4" -> {
                println("Введите Id книги для выдачи")
                val bookId = readLine()
                println("Введите Id читателя для выдачи")
                val readerId = readLine()
                library.giveBook(bookId, readerId)
            }
            "5" -> {
                println("Введите id книги для возврата")
                val bookId = readLine()
                library.returnBook(bookId)
            }
            "6" -> {
                library.showAllBooks()
            }
            "7" -> {
                library.showAllReader()
            }
            "0" -> {
                return
            }

        }
    }
}