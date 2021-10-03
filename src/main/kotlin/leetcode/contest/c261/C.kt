package leetcode.contest.c261

import utils.print

fun main() {
    val s = Solution5892()
//    s.stoneGameIX(intArrayOf(20, 3, 20, 17, 2, 12, 15, 17, 4)).print()
    s.stoneGameIX(intArrayOf(1, 1, 1, 1, 1, 3)).print()
//    s.stoneGameIX(intArrayOf(5, 1, 2, 4, 3)).print()
}

// 博弈论
// 石子游戏
// 从必胜法开始考虑
class Solution5892 {
    fun stoneGameIX(stones: IntArray): Boolean {
        val arr = IntArray(3)
        for (x in stones)
            arr[x % 3] += 1
        arr.print()
        // Alice 先手，无石子可选
        if (arr[1] == 0 && arr[2] == 0) return false
        if (arr[0] % 2 == 0) {
            // Alice 先手，选1必胜
            if (arr[1] != 0 && arr[1] - 1 < arr[2]) return true
            // Alice 先手，选2必胜
            if (arr[2] != 0 && arr[2] - 1 < arr[1]) return true
        } else {
            // Alice 先手，选1必胜（过渡回上述状态）
            if (arr[1] != 0 && arr[2] < arr[1] - 2) return true
            if (arr[2] != 0 && arr[1] < arr[2] - 2) return true
        }
        return false
    }

//    fun stoneGameIX(stones: IntArray): Boolean {
//        val arr = IntArray(3)
//        stones.forEach {
//            arr[it % 3]++
//        }
//        if (arr[1] == 0 && arr[2] == 0) return false
//        if (arr[1] != 0 || arr[2] != 0) arr[0] %= 2
//        val min = minOf(arr[1] / 12, arr[2] / 12)
//        arr[1] -= min * 12
//        arr[1] -= min * 12
//        println(arr.joinToString())
//        val seen = HashMap<String, Boolean>()
//        fun dfs(cur: Int, left: IntArray, step: Boolean): Boolean {
//            val key = "$cur, ${left.joinToString()} $step"
//            if (key in seen) return seen[key]!!
//
//            if (cur > 0 && cur % 3 == 0) {
//                return step
//            }
//            if (left.sum() == 0) {
//                return false
//            }
//            if (!step) {
//                var ans = true
//                for (i in 0 until 3) {
//                    if (left[i] == 0) continue
//                    val next = left.clone() as IntArray
//                    next[i]--
//                    ans = ans and dfs(cur + i, next, !step)
//                }
//                return ans
//            }
//            var ans = false
//            for (i in 0 until 3) {
//                if (left[i] == 0) continue
//                val next = left.clone() as IntArray
//                next[i]--
//                ans = ans or dfs((cur + i) % 3 + 3, next, !step)
//            }
//            return ans.also {
//                seen[key] = it
//            }
//        }
//        return dfs(0, arr, true)
//    }
}