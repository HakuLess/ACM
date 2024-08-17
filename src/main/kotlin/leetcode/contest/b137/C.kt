package leetcode.contest.b137

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionC()
    s.maximumValueSum("[[-3,1,1,1],[-3,1,-3,1],[-3,2,1,1]]".toGrid()).print()
    s.maximumValueSum("[[1,2,3],[4,5,6],[7,8,9]]".toGrid()).print()
}

class SolutionC {
    fun maximumValueSum(board: Array<IntArray>): Long {
        val pq = PriorityQueue<Triple<Long, Int, Int>>(compareBy({ -it.first }))
        for (i in board.indices) {
            for (j in board[0].indices) {
                pq.offer(Triple(board[i][j].toLong(), i, j))
            }
        }

        val arr = ArrayList<Triple<Long, Int, Int>>()

        val arrX = IntArray(board.size)
        val arrY = IntArray(board[0].size)

        while (pq.isNotEmpty()) {
            val item = pq.poll()
            val x = item.second
            val y = item.third

            arrX[x]++
            arrY[y]++
            if (arrX[x] <= 3 && arrY[y] <= 3) {
                arr.add(item)
            }
            if (arr.size >= 9) break
        }

        var ans = Long.MIN_VALUE
        for (i in arr.indices) {
            for (j in i + 1 until arr.size) {
                for (k in j + 1 until arr.size) {
                    val setX = hashSetOf<Int>()
                    val setY = hashSetOf<Int>()
                    val a = arr[i]
                    setX.add(a.second)
                    setY.add(a.third)
                    val b = arr[j]
                    setX.add(b.second)
                    setY.add(b.third)
                    val c = arr[k]
                    setX.add(c.second)
                    setY.add(c.third)
                    if (setX.size == 3 && setY.size == 3) {
                        ans = maxOf(ans, a.first + b.first + c.first)
                    }
                }
            }
        }
        return ans
    }
}