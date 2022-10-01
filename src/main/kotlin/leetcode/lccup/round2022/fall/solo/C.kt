package leetcode.lccup.round2022.fall.solo

import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
    s.ballGame(4, arrayOf("..E.", ".EOW", "..W.")).print()
    s.ballGame(4, arrayOf(".....", "..E..", ".WO..", ".....")).print()
    s.ballGame(3, arrayOf(".....", "....O", "....O", ".....")).print()
    s.ballGame(1, arrayOf(".....", "..O..", ".....")).print()
    s.ballGame(
        41,
        arrayOf("E...W..WW", ".E...O...", "...WO...W", "..OWW.O..", ".W.WO.W.E", "O..O.W...", ".OO...W..", "..EW.WEE.")
    ).print()
}

class SolutionC {
    fun ballGame(num: Int, plate: Array<String>): Array<IntArray> {
        val n = plate.size
        val m = plate[0].length
        val start = ArrayList<Pair<Int, Int>>()
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (plate[i][j] == 'O') {
                    start.add(Pair(i, j))
                }
            }
        }
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList<Triple<Int, Int, Int>>()
        for (i in 0 until 4) {
            start.forEach {
                queue.offer(Triple(it.first, it.second, i))
            }
        }
        var step = -1
        val ans = ArrayList<IntArray>()
        val seen = HashSet<Int>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            if (step > num) return ans.toTypedArray()
            for (i in 0 until size) {
                val item = queue.poll()
                if (item.first * 10000 + item.second * 10 + item.third in seen) continue
                seen.add(item.first * 10000 + item.second * 10 + item.third)
                val (x, y, z) = item
//                println("item ${Triple(x, y, z)}")
                var ori = z
                if (x !in plate.indices || y !in plate[0].indices) {
                    ans.add(intArrayOf(x, y))
                    continue
                }
                when (plate[x][y]) {
                    'W' -> ori++
                    'E' -> ori--
                }
                d4[(ori + 4) % 4].let {
//                    println("from ${Pair(x, y)} to ${Pair(x + it[0], y + it[1])} with ${plate[x][y]} and ori ${(ori + 4) % 4}")
//                    println("offer ${Triple(x + it[0], y + it[1], (ori + 4) % 4)}")
                    val nextX = x + it[0]
                    val nextY = y + it[1]
                    if (nextX !in plate.indices || nextY !in plate[0].indices) {
                        if (x == 0 && y == 0) {

                        } else if (x == 0 && y == m - 1) {

                        } else if (x == n - 1 && y == 0) {

                        } else if (x == n - 1 && y == m - 1) {

                        } else if (Pair(x, y) in start) {

                        } else {
                            println("add $x $y with $ori")
                            if (plate[x][y] == '.') {
                                ans.add(intArrayOf(x, y))
                            } else {

                            }
                        }
                    } else {
                        queue.offer(Triple(x + it[0], y + it[1], (ori + 4) % 4))
                    }
                }
            }
        }
        return ans.toTypedArray()
    }
}

val d4 = arrayOf(
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(1, 0),
    intArrayOf(0, -1)
)