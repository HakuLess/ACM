package leetcode.contest.c300

import utils.ListNode
import utils.print
import utils.toListNode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
fun main() {
    val s = SolutionB()
    s.spiralMatrix(10, 1, intArrayOf(8, 24, 5, 21, 10, 11, 11, 12, 6, 17).toListNode()).print()
}

class SolutionB {
    fun spiralMatrix(m: Int, n: Int, head: ListNode?): Array<IntArray> {
        val arr = Array<IntArray>(m) { IntArray(n) { -1 } }
        var cur = head
        var x = 0
        var y = 0
        val dir4 = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, 0)
        )
        var d = 0
        val seen = HashSet<Int>()
        while (cur != null) {
            arr[x][y] = cur.`val`
            seen.add(x * 2000 + y)
            var step = 0
            while (true) {
                step++
                if (step > 6) break
                val nextX = x + dir4[d][0]
                val nextY = y + dir4[d][1]
                if (nextX in 0 until m && nextY in 0 until n && nextX * 2000 + nextY !in seen) {
                    x = nextX
                    y = nextY
                    break
                } else {
                    d++
                    d %= 4
                }
            }
            cur = cur.next
        }
        return arr
    }
}