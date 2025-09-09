fun main() {
    val library = Library()
    while(true){
        println("""
            Выберите действие:
            1) Добавить книгу
            2) Добавить автора
            3) Найти книгу
            6) Показать все книги
            0) Выход
        """.trimIndent())
        when(readLine()?.trim()){
            "1" -> {
                println("Введите название книги")
                library.addBook(readLine())
            }
            "2" -> {
                println("Введите имя автора")
                library.addReader(readLine())
            }
            "3" ->{
                println("Введите название книги")
                library.findBook(readLine())
            }
            "6" -> {
                library.showAllBooks()
            }
            "0" -> {
                return
            }

        }
    }
}