package leetcode.contest.b64

import utils.SegmentTree
import utils.print
import utils.toGrid

fun main() {
    val s = Solution2055()
    s.platesBetweenCandles("**|**|***|", "[[2,5],[5,9]]".toGrid()).print()
}

class Solution2055 {
    fun platesBetweenCandles(s: String, queries: Array<IntArray>): IntArray {
        // 蜡烛最左侧index 蜡烛最右侧index 总蜡烛数
        val root = SegmentTree<Triple<Int, Int, Int>>(start = 0, end = s.length) { a, b ->
            Triple(
                if (a.first == -1) b.first else if (b.first == -1) a.first else minOf(a.first, b.first),
                if (a.second == -1) b.second else if (b.second == -1) a.second else maxOf(a.second, b.second),
                a.third + b.third
            )
        }
        for (i in s.indices) {
            root.update(root, i, if (s[i] == '|') Triple(i, i, 1) else Triple(-1, -1, 0))
        }
        val ans = ArrayList<Int>()
        queries.forEach {
            ans.add(
                root.query(root, it[0], it[1]).let { item ->
                    if (item.first == -1 || item.second == -1) 0
                    else item.second - item.first - item.third + 1
                }
            )
        }
        return ans.toIntArray()
    }
}