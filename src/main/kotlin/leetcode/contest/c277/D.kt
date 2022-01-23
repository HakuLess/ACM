package leetcode.contest.c277

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.maximumGood("[[2,1,2],[1,2,2],[2,0,2]]".toGrid()).print()
//    s.maximumGood("[2,0],[0,2]".toGrid()).print()
//    s.maximumGood("[[2,0,2,2,0],[2,2,2,1,2],[2,2,2,1,2],[1,2,0,2,2],[1,0,2,1,2]]".toGrid()).print()
//    s.maximumGood("[[2,2],[1,2]]".toGrid()).print()
}

// 状态压缩
// 二进制
// 能用字符串就直接用字符串吧...
class SolutionD {
    fun maximumGood(statements: Array<IntArray>): Int {
        val n = statements.size
        fun check(mask: Int): Boolean {
//            val m = mask.toString(2).padStart(n, '0')
            for (i in statements.indices) {
                // 只管好人说的话 有没有悖论
                // 假设第i个人是好人
//                if (m[i] != '0') {
                if ((mask shr i) and 1 != 0) {
                    for (j in statements[i].indices) {
                        // i 认为 j 是好人
//                        if (statements[i][j] == 1 && m[j] == '0') {
                        if (statements[i][j] == 1 && (mask shr j) and 1 == 0) {
                            return false
                        }
                        // i 认为 j 是坏人
//                        if (statements[i][j] == 0 && m[j] == '1') {
                        if (statements[i][j] == 0 && (mask shr j) and 1 == 1) {
                            return false
                        }
                    }
                }
            }
            return true
        }

        var ans = 0
        for (mask in 0 until (1 shl n)) {
            // 1是好人 0是坏人
            val m = mask.toString(2).padStart(n, '0')
            if (check(mask)) {
                ans = maxOf(ans, m.count { it == '1' })
            }
        }
        return ans
    }
}