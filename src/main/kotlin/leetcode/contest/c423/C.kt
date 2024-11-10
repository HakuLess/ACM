package leetcode.contest.c423

import utils.print

fun main() {
    val s = SolutionC()
    // 14
    s.sumOfGoodSubsequences(intArrayOf(1, 2, 1)).print()
    // 40
    s.sumOfGoodSubsequences(intArrayOf(3, 4, 5)).print()
    // 155
    s.sumOfGoodSubsequences(intArrayOf(6, 7, 8, 7)).print()
}

class SolutionC {
    fun sumOfGoodSubsequences(nums: IntArray): Int {
        val mod = 1_000_000_007L

        // 存入 v 对应 index 列表
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in nums.indices) {
            val v = nums[i]
            map[v] = map.getOrDefault(v, arrayListOf())
            map[v]!!.add(i)
        }

        val seen = HashMap<Int, Pair<Long, Long>>()
        fun dfs(index: Int): Pair<Long, Long> {

            val key = index
            if (key in seen) return seen[key]!!

            val v = nums[index]
            var tmpT: Long = 1L
            var tmpV: Long = 0L

            map[v + 1]?.forEach {
                if (it > index) {
                    val (t, v) = dfs(it)
                    tmpT += t
                    tmpT %= mod
                    tmpV += v
                    tmpV %= mod
                }
            }
            map[v - 1]?.forEach {
                if (it > index) {
                    val (t, v) = dfs(it)
                    tmpT += t
                    tmpT %= mod
                    tmpV += v
                    tmpV %= mod
                }
            }

            tmpV += tmpT * v
            tmpV %= mod

//            println("$index: $tmpT $tmpV")


            return Pair(tmpT, tmpV).also {
                seen[key] = it
            }
        }

        var ans = 0L
        for (i in nums.indices) {
//            println("$i: ${dfs(i)}")
            ans += dfs(i).second
            ans %= mod
        }
        return (ans % mod).toInt()
    }
}