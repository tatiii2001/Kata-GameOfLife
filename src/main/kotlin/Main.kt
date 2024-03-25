fun main(args: Array<String>) {
    val gameOfLife = GameOfLife.withLiveCells(listOf(
        Pair(0,1),
        Pair(1,0),
        Pair(1,1),
        Pair(4,2),
        Pair(3,2),
        Pair(3,1),
        Pair(4,4)
    ))

    println(gameOfLife)

    do{
        val nextGeneration = gameOfLife.nextGeneration()
        println(nextGeneration)
    }while (!nextGeneration.areTheyAllDead())

}
