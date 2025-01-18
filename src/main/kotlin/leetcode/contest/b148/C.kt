package leetcode.contest.b148

import utils.print
import utils.toGraph
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
//    s.longestSpecialPath("[[0,1,2],[1,2,3],[1,3,5],[1,4,4],[2,5,6]]".toGrid(), intArrayOf(2, 1, 2, 1, 3, 1)).print()
//    s.longestSpecialPath("[[1,0,8]]".toGrid(), intArrayOf(2, 2)).print()
//    // [11,3]
//    s.longestSpecialPath("[[1,0,1],[3,1,10],[3,2,1]]".toGrid(), intArrayOf(4, 5, 4, 2)).print()
//
//    // [9,2]
//    s.longestSpecialPath("[[1,0,4],[0,2,9]]".toGrid(), intArrayOf(4, 3, 1)).print()

    // [6,2]
    s.longestSpecialPath("[[3,0,5],[3,1,6],[2,3,5]]".toGrid(), intArrayOf(2, 2, 2, 1)).print()
}

class SolutionC {
    fun longestSpecialPath(edges: Array<IntArray>, nums: IntArray): IntArray {

        val n = edges.size + 1
        val g = edges.toGraph(n)

        var ans = intArrayOf(0, 1)

        fun dfs(pre: Int, cur: Int, total: Int, list: ArrayList<Pair<Int, Int>>, seen: HashSet<Int>) {
            g.adj[cur].forEach { next ->

                if (next == pre) return@forEach

                if (nums[cur] == nums[next]) {
                    dfs(cur, next, 0, arrayListOf(), hashSetOf(nums[cur]))
                    return@forEach
                }

                var curTotal = total
//                val clone = list.clone() as ArrayList<Pair<Int, Int>>
                val clone = list
                val distance = g.weight[cur]!![next]!!

                val removeList = ArrayList<Pair<Int, Int>>()
                val removeSet = HashSet<Int>()

//                println(" $cur -> $next ${nums[next]} in ${seen.joinToString()} with ${nums[next] in seen}")

                while (nums[next] in seen) {
                    val remove = clone.removeAt(0)
                    curTotal -= remove.second

                    removeList.add(remove)
                    seen.remove(remove.first)
                    removeSet.add(remove.first)
                }


                clone.add(Pair(nums[cur], distance))
                curTotal += distance
                seen.add(nums[next])
                dfs(cur, next, curTotal, clone, seen)
                seen.remove(nums[next])

//                println("pre is $pre , cur is $cur [${nums[cur]}], next is $next [${nums[next]}] with $curTotal and ${clone.joinToString()}")

                if (curTotal > ans[0]) {
                    ans = intArrayOf(curTotal, clone.size + 1)
                } else if (curTotal == ans[0]) {
                    ans[1] = minOf(ans[1], clone.size + 1)
                }


                // 执行完成逻辑，将删除数据加回来
                clone.removeLast()
                list.addAll(0, removeList)
                seen.addAll(removeSet)

            }
        }

        dfs(-1, 0, 0, arrayListOf(), hashSetOf(nums[0]))

        return ans
    }
}