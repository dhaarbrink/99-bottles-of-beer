import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

var sharedCounter: Int = 0

fun main() = runBlocking {
    var numberOfBottles = 99
    sharedCounter = numberOfBottles

    val randomNumbers: MutableList<Int> = (0..numberOfBottles).shuffled().toMutableList()

    repeat(numberOfBottles + 1) {
        launch {
            println(getLine(randomNumbers.removeFirst()))
        }
    }
}

suspend fun waitForTurn(bottle: Int, action: () -> String): String {
    do {
        delay(50L)
    } while (sharedCounter != bottle)
    sharedCounter--
    return action()
}

suspend fun getLine(bottles: Int): String {
    return waitForTurn(bottles) {
        val plural = pluralBottle(bottles)
        val pluralMinusOne = pluralBottle(bottles - 1)

        return@waitForTurn if (bottles > 0)
            """
                $bottles $plural of beer on the wall, $bottles $plural of beer.
                Take one down, pass it around, ${bottles - 1} $pluralMinusOne of beer on the wall.
                
            """.trimIndent()
        else
            """
                No more bottles of beer on the wall, no more bottles of beer.
                There's nothing else to fall, because there's no more bottles of beer on the wall.
                
            """.trimIndent()
    }
}

fun pluralBottle(bottles: Int): String = if (bottles == 1) "bottle" else "bottles"