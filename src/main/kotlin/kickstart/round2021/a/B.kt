package kickstart.round2021.a

// L Shaped Plots
fun main() {

    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (r, c) = readLine()!!.trim().split(' ').map { it.toInt() }
        val matrix = ArrayList<IntArray>()
        repeat(r) {
            matrix.add(readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray())
        }

        // 计算上下左右连续的1的矩阵
        val left = Array<IntArray>(r) { IntArray(c) }
        val right = Array<IntArray>(r) { IntArray(c) }
        val top = Array<IntArray>(r) { IntArray(c) }
        val down = Array<IntArray>(r) { IntArray(c) }

        for (i in 0 until r) {
            for (j in 0 until c) {
                left[i][j] = if (matrix[i][j] == 1) (left[i].getOrElse(j - 1) { 0 } + 1) else 0
            }
        }

        for (i in 0 until r) {
            for (j in c - 1 downTo 0) {
                right[i][j] = if (matrix[i][j] == 1) (right[i].getOrElse(j + 1) { 0 } + 1) else 0
            }
        }

        for (j in 0 until c) {
            for (i in 0 until r) {
                top[i][j] = if (matrix[i][j] == 1) ((top.getOrNull(i - 1)?.get(j) ?: 0) + 1) else 0
            }
        }

        for (j in 0 until c) {
            for (i in r - 1 downTo 0) {
                down[i][j] = if (matrix[i][j] == 1) ((down.getOrNull(i + 1)?.get(j) ?: 0) + 1) else 0
            }
        }

        var ans = 0

        fun check(x: Int, y: Int): Int {
            val a = maxOf(x, y)
            val b = minOf(x, y)
            if (a >= 4 && b >= 2) {
                return minOf(a / 2 - 1, b - 1) + minOf(b / 2 - 1, a - 1)
            }
            return 0
        }

        for (i in 0 until r) {
            for (j in 0 until c) {
                val a0 = top[i][j]
                val a1 = down[i][j]
                val a2 = left[i][j]
                val a3 = right[i][j]

                ans += check(a0, a2)
                ans += check(a0, a3)
                ans += check(a1, a2)
                ans += check(a1, a3)
            }
        }
        println("Case #${it + 1}: ${ans}")
    }
}