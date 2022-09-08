
var contPrice = 0
var income = 0
var totalIncome = 0
var option = 0
var row: UInt = 0u
var seat: UInt = 0u
var linha: Int = 0
var coluna: Int = 0
val lista = mutableListOf(
    mutableListOf(" ", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
    mutableListOf("1", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
    mutableListOf("2", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
    mutableListOf("3", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
    mutableListOf("4", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
    mutableListOf("5", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
    mutableListOf("6", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
    mutableListOf("7", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
    mutableListOf("8", "S", "S", "S", "S", "S", "S", "S", "S", "S"),
    mutableListOf("9", "S", "S", "S", "S", "S", "S", "S", "S", "S")
)

fun assento(row: UInt, seat: UInt, lista: MutableList<MutableList<String>>) {
    println("Cinema:")
    for (i in 0 .. row.toInt()){
        for (j in 0 .. seat.toInt()){
            print(lista[i][j] + " ")
        }
        println(" ")
    }
}

fun price() {
    val totalSeat = row * seat
    val lugar = row.toInt().toDouble()
    if (totalSeat <= 60u) {
        val ticket = 10
        income += ticket
        totalIncome = totalSeat.toInt() * ticket
        println("Ticket Price: $$ticket")
    } else if (totalSeat > 60u && linha < lugar/2) {
        val ticket = 10
        income += ticket
        println("Ticket Price: $$ticket")
    } else if (totalSeat > 60u && linha > lugar/2) {
        val ticket = 8
        income += ticket
        println("Ticket Price: $$ticket")
    }
}

fun totalPrice() {
    val total = row.toInt() * seat.toInt()
    if (total <= 60) {
        totalIncome = total * 10
    } else if (total > 60 && row % 2u == 0u) {
        totalIncome = (total / 2) * 10 + (total / 2) * 8
    } else if (total > 60 && row % 2u != 0u) {
        val half = row / 2u
        val firstHalf = half * seat * 10u
        val secondHalf = (row - half) * seat * 8u
        totalIncome = firstHalf.toInt() + secondHalf.toInt()
    }
}

fun main() {

    //lê o número de assentos em cada linha e coluna
    println("Enter the number of rows: ")
    row = readLine()!!.toUInt()
    println("Enter the number of seats in each row: ")
    seat = readLine()!!.toUInt()

    while ((option>= 0) && (option<= 3)) {
        println("\n1. Show the seats" +
                "\n2. Buy a ticket" +
                "\n3. Statistics" +
                "\n0. Exit")
        option = readln().toInt()
        println(" ")
        when (option) {
            1 -> {
                assento(row, seat, lista)
                println(" ")
            }
            2 -> {
                do {
                    var validation = 0
                    try {
                        do {
                            println("Enter a row number: ")
                            linha = readln().toInt()
                            println("Enter a seat number in that row: ")
                            coluna = readln().toInt()
                            if (lista[linha][coluna] == "B") {
                                println("That ticket has already been purchased!")
                            }
                        } while (lista[linha][coluna] == "B")
                    } catch (e: IndexOutOfBoundsException) {
                        println("Wrong input!")
                        validation = 1
                    }
                } while (validation == 1)
                lista[linha][coluna] = "B"
                price()
                contPrice += 1
                println(" ")
            }
            3 -> {
                totalPrice()
                println("Number of purchased tickets: $contPrice")
                val percentage = (contPrice.toDouble() / (row * seat).toDouble()) * 100
                val formatPercentage = "%.2f".format(percentage)
                println("Percentage: $formatPercentage%")
                println("Current income: $$income")
                println("Total income: $$totalIncome")
            }
            0 -> break
        }
    }
}