package leetcode.contest.c413

import utils.Trie
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
    // 31
    s.maxScore("[[5],[7],[19],[5]]".toGrid().map { it.toList() }).print()
    // 69
    s.maxScore("[[1,5,20,18],[19,6,17,3],[12,10,6,3],[1,20,12,15]]".toGrid().map { it.toList() }).print()
    // 15
    s.maxScore("[[8,7,6],[8,3,2]]".toGrid().map { it.toList() }).print()
}

class SolutionC {
    fun maxScore(grid: List<List<Int>>): Int {
        // 值 & 第二大差值 & 所在行数
        val list = ArrayList<Pair<Int, Int>>()
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                list.add(Pair(grid[i][j], i))
            }
        }
        list.sortWith(compareBy({ -it.first }, { it.second }))


        fun dfs(curList: ArrayList<Pair<Int, Int>>): Int {
//            println("enter ${curList.joinToString()}")
            var ans = 0
            while (curList.isNotEmpty()) {
                while (curList.size >= 2) {
                    // 同行相等，直接移除第一个
                    if (curList[0].first == curList[1].first && curList[0].second == curList[1].second) {
                        curList.removeAt(0)
                    } else {
                        break
                    }
                }

                if (curList.size >= 2) {
                    // 非同行相等，DFS分叉
                    if (curList[0].first == curList[1].first && curList[0].second != curList[1].second) {
                        val list0 = curList.clone() as ArrayList<Pair<Int, Int>>
                        list0.removeAt(0)
                        val list1 = curList.clone() as ArrayList<Pair<Int, Int>>
                        list1.removeAt(1)
//                        println("分选 ${list0.joinToString()} === ${list1.joinToString()}  ${seenV.joinToString()} ${seenRow.joinToString()}")
                        val ans0 = dfs(list0)
                        val ans1 = dfs(list1)
//                        println("分选结果 $ans0 with $ans1")
                        return ans + maxOf(ans0, ans1)
                    }
                }

                // 无相等场景，贪心拿最大值
                val item = curList.removeAt(0)
                ans += item.first
                curList.removeIf {
                    it.first == item.first || it.second == item.second
                }
            }
            return ans
        }

        return dfs(list)
    }

//    fun maxScore(grid: List<List<Int>>): Int {
//        val n = grid.size
//        val m = grid[0].size
//        val visited = Array(n) { BooleanArray(m) { false } }
//        var maxScore = 0
//
//        val seen = HashSet<String>()
//        fun dfs(row: Int, currentScore: Int, usedValues: Set<Int>) {
//            val key = "$row ${usedValues.joinToString(";")}"
//            if (key in seen) return
//            seen.add(key)
//            if (row == n) {
//                maxScore = maxOf(maxScore, currentScore)
//                return
//            }
//            for (col in 0 until m) {
//                val value = grid[row][col]
//                if (value !in usedValues) {
//                    visited[row][col] = true
//                    dfs(row + 1, currentScore + value, usedValues + value)
//                    visited[row][col] = false
//                }
//                dfs(row + 1, currentScore, usedValues)
//            }
//        }
//
//        dfs(0, 0, emptySet())
//        return maxScore
//    }
}