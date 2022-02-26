package atcoder.abc241

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val matrix = Array<IntArray>(n) { IntArray(n) }
    repeat(n) {
        val arr = readLine()!!.map { if (it == '#') 1 else 0 }.toIntArray()
        matrix[it] = arr
    }

    val dir4 = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(0, 1),
        intArrayOf(1, 1),
        intArrayOf(-1, 1)
    )

    for (i in 0 until n) {
        for (j in 0 until n) {
            dir4.forEach {
                var cur = 0
                if (i + it[0] * 5 in 0 until n && j + it[1] * 5 in 0 until n) {
                    for (step in 0..5) {
                        cur += matrix[i + it[0] * step][j + it[1] * step]
                    }
                }
                if (cur >= 4) {
                    println("Yes")
                    return
                }
            }
        }
    }
    println("No")
}