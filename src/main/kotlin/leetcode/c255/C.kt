package leetcode.c255

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = Solution5852()
//    s.minimizeTheDifference("[[1,2,3],[4,5,6],[7,8,9]]".toGrid(), 13).print()
//    s.minimizeTheDifference("[[1,2,9,8,7]]".toGrid(), 6).print()

    s.minimizeTheDifference(
        "[[10,3,7,7,9,6,9,8,9,5],[1,1,6,8,6,7,7,9,3,9],[3,4,4,1,3,6,3,3,9,9],[6,9,9,3,8,7,9,6,10,6]]".toGrid(),
        5
    ).print()
}

class Solution5852 {
    fun minimizeTheDifference(mat: Array<IntArray>, target: Int): Int {
        var set = TreeSet<Int>()
        for (i in mat.indices) {
            val nxtSet = TreeSet<Int>()
            for (j in mat[0].indices) {
                if (i == 0) {
                    nxtSet.add(mat[i][j])
                } else {
                    set.forEach { s ->
                        val nxt = s + mat[i][j]
                        nxtSet.add(nxt)
                    }
                }
            }
            var high = nxtSet.ceiling(target)
            while (high != null) {
                high = nxtSet.ceiling(high + 1)
                if (high != null)
                    nxtSet.remove(high)
            }
            set = nxtSet
        }

        set.print()
        var ans = Int.MIN_VALUE / 2
        ans = minOf(
            (set.ceiling(target) ?: Int.MAX_VALUE / 4) - target,
            target - (set.floor(target) ?: Int.MIN_VALUE / 4)
        )
        return ans
    }
}