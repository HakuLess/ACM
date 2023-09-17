package leetcode.contest.c363

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.maxNumberOfAlloys(3, 2, 15, "[[1,1,1],[1,1,10]]".toGrid().map { it.toList() }, listOf(0, 0, 100), listOf(1, 2, 3))
        .print()
}

class SolutionC {
    fun maxNumberOfAlloys(
        n: Int,
        k: Int,
        budget: Int,
        composition: List<List<Int>>,
        stock: List<Int>,
        cost: List<Int>
    ): Int {

        var ans = 0
        for (i in composition.indices) {
            var money = budget
            var tmp = 0
            val st = stock.toIntArray()
            while (money >= 0) {
                for (j in st.indices) {
                    st[j] -= composition[i][j]
                    if (st[j] < 0) {
                        money += st[j] * cost[j]
                        st[j] = 0
                    }
                }
                if (money >= 0)
                    tmp++
            }
            ans = maxOf(ans, tmp)
        }
        return ans
    }
}