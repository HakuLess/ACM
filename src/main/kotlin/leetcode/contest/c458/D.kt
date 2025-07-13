package leetcode.contest.c458

import utils.isPalindrome
import utils.print
import utils.toGraph
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.maxLen(3, "[[0,1],[1,2]]".toGrid(), "aba").print()
//    s.maxLen(3, "[[0,1],[1,2]]".toGrid(), "abc").print()
//    s.maxLen(4, "[[0,2],[0,3],[3,1]]".toGrid(), "bbac").print()
//
//    // 3
//    s.maxLen(3, "[[1,0],[2,1],[0,2]]".toGrid(), "hjj").print()

    // 2
    s.maxLen(3, "[[2,0],[2,1]]".toGrid(), "mll").print()

    // TODO 处理空状态
//    s.maxLen(1, "[]".toGrid(), "z").print()
}

class SolutionD {

    fun maxLen(n: Int, edges: Array<IntArray>, label: String): Int {

        if (edges.isEmpty()) return 1

        val graph = edges.toGraph(n)

        var ans = 1

        // 对每个中心点做双向 DFS
        for (center in 0 until n) {
            val visited = 1 shl center
            val leftMap = ArrayList<Pair<String, Int>>()
            val rightMap = ArrayList<Pair<String, Int>>()

            // DFS 左右路径（分别记录前缀和后缀）
            fun dfs(node: Int, path: String, mask: Int, map: ArrayList<Pair<String, Int>>, depth: Int) {
                map.add(Pair(path, mask))
                if (depth == 6) return
                for (next in graph.adj[node]) {
                    if ((mask shr next) and 1 == 0) {
                        dfs(next, path + label[next], mask or (1 shl next), map, depth + 1)
                    }
                }
            }

            dfs(center, "", visited, leftMap, 0)
            dfs(center, "", visited, rightMap, 0)

//            leftMap.forEach {
//                println(it)
//            }
//            println()
//            rightMap.forEach {
//                println(it)
//            }
//            println()
//            println("=============")

            for (i in leftMap.indices) {
                for (j in rightMap.indices) {
                    val (strL, a) = leftMap[i]
                    val (strR, b) = rightMap[j]

                    if (a and b xor (1 shl center) == 0) {
//                        println("left is $strL right is $strR")
                        val str = "${strL.reversed()}${label[center]}${strR}"
                        if (str.isPalindrome()) {
//                            println("$str is Pail")
                            ans = maxOf(ans, str.length)
                        }
                    }
                }
            }
        }

        return ans
    }

//    fun maxLen(n: Int, edges: Array<IntArray>, label: String): Int {
//
//        if (edges.isEmpty()) return 1
//
//        val graph = edges.toGraph(n)
//        var ans = 1
//
//        // 整体去重
//        val seen = HashSet<Triple<Int, Int, String>>()
//
//        fun dfs(node: Int, visited: Int, path: MutableList<Char>) {
//            // 已暴力找到最大值
//            if (ans == n) return
//
//            if (path.isPalindrome()) {
//                ans = maxOf(ans, path.size)
//            }
//
//            for (next in graph.adj[node]) {
//                val key = Triple(visited, next, path.joinToString(""))
//                if ((visited shr next) and 1 == 0 && key !in seen) {
//                    seen.add(key)
//                    path.add(label[next])
//                    dfs(next, visited or (1 shl next), path)
//                    path.removeLast()
//                }
//            }
//        }
//
//        for (i in 0 until n) {
//            dfs(i, 1 shl i, mutableListOf(label[i]))
//        }
//
//        return ans
//    }
}