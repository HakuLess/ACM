package leetcode.contest.c257

import utils.print
import utils.toGrid

fun main() {
    val s = Solution5864()
//    s.numberOfWeakCharacters("[[5,5],[6,3],[3,6]]".toGrid()).print()
    s.numberOfWeakCharacters("[[7,7],[1,2],[9,7],[7,3],[3,10],[9,8],[8,10],[4,3],[1,5],[1,5]]".toGrid()).print()
}

class Solution5864 {
    fun numberOfWeakCharacters(properties: Array<IntArray>): Int {
        val st = properties.sortedWith(compareBy({ -it[0] }, { it[1] }))
        var ans = 0
        var maxDef = st[0][1]
        for (i in 1 until st.size) {
            if (st[i][1] < maxDef)
                ans++
            maxDef = maxOf(maxDef, st[i][1])
        }
        return ans
    }
}