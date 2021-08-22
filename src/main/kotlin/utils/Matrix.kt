package utils

// n行 m列 矩阵
class Matrix(val n: Int, val m: Int, val matrix: Array<IntArray>) {

    override fun toString(): String {
        return matrix.joinToString { it.joinToString() }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix) return false
        return this.toString() == other.toString()
    }

    override fun hashCode(): Int {
        var result = n
        result = 31 * result + m
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }

    operator fun contains(x: Int): Boolean {
        return matrix.any {
            x in it
        }
    }
}

fun Matrix.count(func: (x: Int) -> Boolean): Int {
    var ans = 0
    matrix.forEach {
        ans += it.count { func(it) }
    }
    return ans
}

/**
 * 矩阵内部求和
 * @param from 起始点
 * @param to 终止点
 * */
fun Matrix.sum(from: Pair<Int, Int>, to: Pair<Int, Int>): Long {
    val ori = Pair(
        when {
            to.first - from.first == 0 -> 0
            to.first - from.first > 0 -> 1
            else -> -1
        },
        when {
            to.second - from.second == 0 -> 0
            to.second - from.second > 0 -> 1
            else -> -1
        }
    )
    var cur = from
    var sum = this.matrix[cur.first][cur.second].toLong()
    while (cur != to) {
        cur = Pair(cur.first + ori.first, cur.second + ori.second)
        sum += this.matrix[cur.first][cur.second]
    }
    return sum
}

/**
 * 截取子矩阵
 * @param i 横坐标（外层）
 * @param j 纵坐标（内层）
 * @param w 宽度（与i计算）
 * @param h 高度（与j计算）
 * */
fun Matrix.subMatrix(i: Int, j: Int, w: Int, h: Int): Matrix {
    val mat = Array<IntArray>(w) { IntArray(h) }
    for (a in i until i + w) {
        for (b in j until j + h) {
            mat[a - i][b - j] = this.matrix[a][b]
        }
    }
    return Matrix(w, h, mat)
}

// 向右旋转90°
fun Matrix.rotate() {
    if (n != m) {
        println("Rotate Error")
    }
    for (i in 0 until (n + 1) / 2) {
        for (j in 0 until n / 2) {
            val temp = matrix[n - 1 - j][i]
            matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1]
            matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i]
            matrix[j][n - 1 - i] = matrix[i][j]
            matrix[i][j] = temp
        }
    }
}

fun Array<IntArray>.toMatrix(): Matrix {
    val n = this.size
    val m = this[0].size
    return Matrix(n, m, this)
}

fun String.toMatrix(): Matrix {
    return this.trim(']').split("],[").map {
        it.toIntArray()
    }.toTypedArray().toMatrix()
}