package leetcode.contest.c383

import utils.Matrix
import utils.print
import utils.subMatrixSum
import utils.toGrid
import kotlin.math.abs

fun main() {
    val s = SolutionC()
//    s.resultGrid("[[5,6,7,10],[8,9,10,10],[11,12,13,10]]".toGrid(), 3).print()
//    s.resultGrid("[[10,20,30],[15,25,35],[20,30,40],[25,35,45]]".toGrid(), 12).print()
//    s.resultGrid("[[5,6,7],[8,9,10],[11,12,13]]".toGrid(), 1).print()
//    s.resultGrid("[[0,7,0],[4,6,3],[2,4,5]]".toGrid(), 5).print()
    s.resultGrid("[[0,14,5,15],[20,12,2,11],[8,11,0,3]]".toGrid(), 14).print()
}

class SolutionC {
    fun resultGrid(image: Array<IntArray>, threshold: Int): Array<IntArray> {
        val n = image.size
        val m = image[0].size

        val matrix = Matrix(n, m, image)
        matrix.preSum()

        val ans = Array<IntArray>(n) { IntArray(m) }
        val count = Array<IntArray>(n) { IntArray(m) }
        for (i in 0 until n) {
            for (j in 0 until m) {
                println("enter $i $j")
                if (i + 2 < n && j + 2 < m) {
                    var check = true
                    for (a in 0..2) {
                        for (b in 0..2) {
                            if (a != 0) {
                                if (abs(image[i + a][j + b] - image[i + a - 1][j + b]) > threshold) check = false
                            }
                            if (b != 0) {
                                if (abs(image[i + a][j + b] - image[i + a][j + b - 1]) > threshold) check = false
                            }
                        }
                    }
                    println("$i $j check $check")
                    if (!check) continue

                    val sum = matrix.subMatrixSum(i, j, i + 2, j + 2)
                    for (a in 0..2) {
                        for (b in 0..2) {
                            ans[i + a][j + b] += sum / 9
                            count[i + a][j + b]++
                        }
                    }
                    println("$i $j with ")
//                    ans.print()
                }
            }
        }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (count[i][j] == 0) {
                    ans[i][j] = image[i][j]
                } else {
                    ans[i][j] /= count[i][j]
                }
            }
        }
        return ans
    }
}