package leetcode.contest.c260

import utils.eval
import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = Solution5884()
    s.scoreOfStudents("7+3*1*2", intArrayOf(20, 13, 42)).print()
//    s.scoreOfStudents("1+4+4+1*0+2", intArrayOf(7)).print()
//    s.scoreOfStudents("3+5*2", intArrayOf(13, 0, 10, 13, 13, 16, 16)).print()
}

// 表达式计算
class Solution5884 {
//    fun scoreOfStudents(s: String, answers: IntArray): Int {
//        val real = eval(s)
//        val n = s.length
//        val dp = Array<Array<HashSet<Int>>>(n) { Array(n) { HashSet<Int>() } }
//        for (i in 0 until n step 2) {
//            dp[i][i].add(s[i] - '0')
//        }
//        // 不断扩大范围
//        for (size in 2 until n step 2) {
//            for (i in 0 until n - size + 1 step 2) {
//                val j = i + size
//                for (k in i + 1 until j step 2) {
//                    val op = s[k]
//                    for (a in dp[i][k - 1]) {
//                        for (b in dp[k + 1][j]) {
//                            if (op == '+') {
//                                if (a + b <= 1000)
//                                    dp[i][j].add(a + b)
//                            } else {
//                                if (a * b <= 1000)
//                                    dp[i][j].add(a * b)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return answers.map {
//            when (it) {
//                real -> 5
//                in dp[0][n - 1] -> 2
//                else -> 0
//            }
//        }.sum()
//    }

    fun scoreOfStudents(s: String, answers: IntArray): Int {
        val real = eval(s)
        val seen = HashMap<String, HashSet<Int>>()
        fun dfs(l: Int, r: Int): HashSet<Int> {
            val key = "$l,$r"
            if (key in seen) return seen[key]!!
            if (r - l == 1)
                return hashSetOf(s.substring(l, r).toInt()).also {
                    seen[key] = it
                }
            val set = HashSet<Int>()
            // i按顺序选中每个符号
            for (i in l + 1..r - 1 step 2) {
                val left = dfs(l, i)
                val right = dfs(i + 1, r)
                left.forEach { a ->
                    right.forEach { b ->
                        if (s[i] == '+') {
                            if (a + b <= 1000)
                                set.add(a + b)
                        } else {
                            if (a * b <= 1000)
                                set.add(a * b)
                        }
                    }
                }
            }
            return set.also {
                seen[key] = it
            }
        }

        val set = dfs(0, s.length)
        return answers.map {
            when (it) {
                real.toInt() -> 5
                in set -> 2
                else -> 0
            }
        }.sum()
    }
}