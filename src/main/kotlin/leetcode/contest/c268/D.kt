package leetcode.contest.c268

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
//    s.kMirror(2, 5).print()
//    s.kMirror(3, 7).print()
    s.kMirror(7, 17).print()
//    s.kMirror(9, 100).print()
}

// 构造回文数字
class SolutionD {
    fun kMirror(k: Int, n: Int): Long {
        var ans = 0L
        var cur = 0
        val queue: Queue<ArrayList<Int>> = LinkedList<ArrayList<Int>>()
        for (i in 1 until k) {
            queue.offer(arrayListOf(i))
        }
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                val valK = item.joinToString("")
                val val10 = valK.toLong(k)
                if (val10.toString() == val10.toString().reversed()) {
                    ans += val10
                    cur++
                    if (cur == n) return ans
                }

                if (item.size % 2 == 0) {
                    for (j in 0 until k) {
                        val next = item.clone() as ArrayList<Int>
                        next.add(item.size / 2, j)
                        queue.offer(next)
                    }
                } else {
                    item.add(item.size / 2, item[item.size / 2])
                    queue.offer(item)
                }
            }
        }
        return 0L
    }
}