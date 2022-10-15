package leetcode.contest.b89

import utils.Graph
import utils.UFS
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.componentValue(intArrayOf(6, 2, 2, 2, 6), "[[0,1],[1,2],[1,3],[3,4]]".toGrid()).print()
//    s.componentValue(intArrayOf(1, 2, 2, 1, 1), "[[0,2],[0,4],[1,4],[2,3]]".toGrid()).print()
    s.componentValue(intArrayOf(1, 2, 1, 1, 1), "[[0,1],[1,3],[3,4],[4,2]]".toGrid()).print()
}

// todo 直接硬DFS
class SolutionD {
    fun componentValue(nums: IntArray, edges: Array<IntArray>): Int {
        if (edges.isEmpty()) return 0
        val n = nums.size
        val g = Graph(n)
        for (edge in edges) {
            g.addEdge(edge[0], edge[1])
        }
        val sum = nums.sum()
        val max = nums.maxOrNull()!!
//        val max = nums.max()!!
        val backs = ArrayList<Int>()
        for (i in max..sum step 1) {
            if (sum % i == 0) {
                backs.add(i)
            }
        }
        if (backs.isEmpty()) return 0

        val arr = IntArray(n)
        for (i in 0 until n) {
            arr[i] = nums[i]
        }

        fun check(v: Int): Boolean {
            val ufs = UFS(n)
            for (edge in edges) {
                val p0 = ufs.find(edge[0])
                val p1 = ufs.find(edge[1])

                if (arr[p0] >= v || arr[p1] >= v) {
                    continue
                } else {
                    val b1 = arr[ufs.find(edge[1])]
                    val b0 = arr[ufs.find(edge[0])]

                    ufs.union(edge[0], edge[1])
                    arr[ufs.find(edge[0])] = b0 + b1
                    arr[ufs.find(edge[1])] = b0 + b1
                }
            }
            println("check $v")
            arr.joinToString().print()
            (0 until n).map { ufs.find(it) }.joinToString().print()
            return (0 until n).all {
                arr[ufs.find(it)] == v
            }
        }
        backs.forEach {
            if (check(it)) {
                return sum / it - 1
            }
        }
        return 0
    }
}