package leetcode.contest.b152

class Spreadsheet(rows: Int) {

    private val grid = Array(rows) { IntArray(26) }

    fun setCell(cell: String, value: Int) {
        val (row, col) = parseCell(cell)
        grid[row][col] = value
    }

    fun resetCell(cell: String) {
        val (row, col) = parseCell(cell)
        grid[row][col] = 0
    }

    fun getValue(formula: String): Int {
        val parts = formula.substring(1).split("+")
        return parts.sumOf { parseValue(it) }
    }

    private fun parseValue(token: String): Int {
        return if (token[0].isLetter()) {
            val (row, col) = parseCell(token)
            grid[row][col]
        } else {
            token.toInt()
        }
    }

    private fun parseCell(cell: String): Pair<Int, Int> {
        val col = cell[0] - 'A'
        val row = cell.substring(1).toInt() - 1
        return row to col
    }

}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * var obj = Spreadsheet(rows)
 * obj.setCell(cell,value)
 * obj.resetCell(cell)
 * var param_3 = obj.getValue(formula)
 */