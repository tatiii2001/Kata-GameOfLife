import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameOfLifeShould {
    /*
    * (alive) neighbours < 2 = dead
    *  + + - -  => - - - -
    *  - - - -     - - - -
    * (alive) neighbours > 3 = dead
    *  + + + -  => + - - -
    *  + + - -     + + - -
    * (alive) neighbours == 2 || 3 = alive
    *  + + - -  => + + - -
    *  + - - -     + + - -
    * (dead) neighbours == 3 = alive
    *  - + - -  => + + - -
    *  + + - -     + + - -
    * */

    @Test
    fun `kill any cell with less than two neighbours`() {
        val gameOfLife = GameOfLife.withLiveCells(listOf(
            Pair(0,0),
            Pair(0,1)
        ))

        val result = gameOfLife.nextGeneration()

        val expectedGameOfLife = GameOfLife()
        assertEquals(expectedGameOfLife, result)
    }
    @Test
    fun `kill any cell with more than three neighbours`() {
        val gameOfLife = GameOfLife.withLiveCells(listOf(
            Pair(0,0),
            Pair(0,1),
            Pair(0,2),
            Pair(1,0),
            Pair(1,1),
        ))

        val result = gameOfLife.nextGeneration()

        val expectedGameOfLife = GameOfLife.withLiveCells(listOf(
            Pair(0,0),
            Pair(1,0),
            Pair(1,1),
        ))
        assertEquals(expectedGameOfLife, result)
    }

    @Test
    fun `keep alive cells with two or three neighbours`() {
        val gameOfLife = GameOfLife.withLiveCells(listOf(
            Pair(0,0),
            Pair(0,1),
            Pair(1,0),
        ))

        val result = gameOfLife.nextGeneration()

        val expectedGameOfLife = GameOfLife.withLiveCells(listOf(
            Pair(0,0),
            Pair(0,1),
            Pair(1,0),
            Pair(1,1)
        ))
        assertEquals(expectedGameOfLife, result)
    }

    @Test
    fun `come alive again a cell with exactly three neighbours`() {
        val gameOfLife = GameOfLife.withLiveCells(listOf(
            Pair(0,1),
            Pair(1,0),
            Pair(1,1)
        ))

        val result = gameOfLife.nextGeneration()

        val expectedGameOfLife = GameOfLife.withLiveCells(listOf(
            Pair(0,0),
            Pair(0,1),
            Pair(1,0),
            Pair(1,1)
        ))
        assertEquals(expectedGameOfLife, result)
    }
}