class GameOfLife(private val cells: Array<Array<String>> = Array(5){Array(5){"-"} }) {

    fun nextGeneration(): GameOfLife {
        val cells = this.cells.copyOf()
        for (i in cells.indices){
            for (j in cells[0].indices){
                val neighboursCount = liveNeighboursFrom(i,j)
                val isUnderpopulation = neighboursCount < 2
                val isOvercrowding = neighboursCount > 3
                if (isUnderpopulation || isOvercrowding) {
                    killCellAt(i, j)
                }
                if (neighboursCount == 3) {
                    comeAlive(i, j)
                }
            }
        }
        return GameOfLife(cells)
    }

    private fun comeAlive(i: Int, j: Int) {
        cells[i][j] = "+"
    }

    private fun killCellAt(i: Int, j: Int) {
        cells[i][j] = "-"
    }

    private fun liveNeighboursFrom(i: Int, j: Int): Int {
        var aliveNeighbours = 0
        (i - 1 .. i + 1).forEach { rowIndex -> (j -1 .. j + 1).forEach { columnIndex ->
            if (isOutsideFromCellsIndices(rowIndex, columnIndex)) return@forEach
            if (isTheSameCell(rowIndex, i, columnIndex, j)) return@forEach
            if (isCellAlive(rowIndex, columnIndex)) aliveNeighbours++
        } }
        return aliveNeighbours
    }

    private fun isCellAlive(rowIndex: Int, columnIndex: Int) = cells[rowIndex][columnIndex] === "+"

    private fun isTheSameCell(rowIndex: Int, i: Int, columnIndex: Int, j: Int) =
        rowIndex == i && columnIndex == j

    private fun isOutsideFromCellsIndices(rowIndex: Int, columnIndex: Int) =
        rowIndex < 0 || rowIndex > cells.size - 1 || columnIndex < 0 || columnIndex > cells[0].size - 1

    fun areTheyAllDead() : Boolean{
        for (i in cells.indices){
            for (j in cells[0].indices){
                if (cells[i][j] == "+") return false
            }
        }
        return true
    }
    override fun toString(): String {
        var cell = ""
        cells.forEach {
            it.forEach { cell += "$it "  }
            cell += "\n"
        }
        return cell
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameOfLife

        for (i in cells.indices) {
            for (j in cells[0].indices) {
                if (cells[i][j] != other.cells[i][j]) return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        return cells.contentDeepHashCode()
    }

    companion object{
        fun withLiveCells(positions: List<Pair<Int, Int>>): GameOfLife {
            val cells = Array(5){Array(5){"-"} }
            positions.forEach {
                cells[it.first][it.second] = "+"
            }
            return GameOfLife(cells)
        }
    }





}
