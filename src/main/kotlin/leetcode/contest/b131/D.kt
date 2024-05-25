package leetcode.contest.b131

import utils.SegmentTree
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
//    s.getResults("[[1,2],[2,3,3],[2,3,1],[2,2,2]]".toGrid()).joinToString().print()
//    s.getResults("[[2,1,6],[1,4],[2,7,5]]".toGrid()).joinToString().print()
    // true true false
    s.getResults("[[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]".toGrid()).joinToString().print()
}

class SolutionD {
    fun getResults(queries: Array<IntArray>): List<Boolean> {
        val ans = ArrayList<Boolean>()

        val ts = TreeSet<Int>()
        ts.add(Int.MAX_VALUE / 10)

        val root = SegmentTree<Int>(0, Int.MAX_VALUE / 2, 0) { a, b ->
            a + b
        }

        queries.forEach {
            if (it[0] == 1) {
                // 插入障碍物
                val index = it[1]
                ts.add(it[1])
                root.update(root, index, index, 1)
            } else {
                val end = it[1]
                val sz = it[2]

                // 1 总能插入
                if (sz == 1) {
                    ans.add(true)
                    return@forEach
                }

                var cur = false
                var start = 0
                while (start + sz <= end) {
//                    println("start $start with $sz,  cmp ${start + sz} with $end")
                    if (root.query(root, start + 1, start + sz - 1) == 0) {
                        cur = true
                        break
                    }

//                    println("before $start  after lager ${start + sz}  with ${ts.floor(start + sz)}")
                    start = ts.floor(start + sz)!!
                }
                ans.add(cur)
            }
        }
        return ans.toTypedArray().toList()
    }
}