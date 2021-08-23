package leetcode.contest.c255

import utils.print

fun main() {
    val s = Solution5853()
    s.recoverArray(3, intArrayOf(-3, -2, -1, 0, 0, 1, 2, 3)).print()
}

class Solution5853 {
    fun recoverArray(n: Int, sums: IntArray): IntArray {
        val target = HashMap<Int, Int>()
        sums.forEach {
            target[it] = target.getOrDefault(it, 0) + 1
        }
        var ans = IntArray(0)
        fun dfs(cur: ArrayList<Int>, index: Int, curMap: HashMap<Int, Int>) {
            if (ans.isNotEmpty()) return
            if (curMap.keys.any { curMap.getOrDefault(it, 0) > target.getOrDefault(it, 0) })
                return
            if (cur.size == n) ans = cur.toIntArray()
            for (i in index until sums.size) {
                cur.add(sums[i])
                val diffMap = HashMap<Int, Int>()
                diffMap[sums[i]] = 1
                curMap.forEach { key, value ->
                    repeat(value) {
                        diffMap[key + sums[i]] = diffMap.getOrDefault(key + sums[i], 0) + 1
                    }
                }
                diffMap.forEach { key, value ->
                    curMap[key] = curMap.getOrDefault(key, 0) + value
                }
                dfs(cur, i + 1, curMap)
                diffMap.forEach { key, value ->
                    curMap[key] = curMap.getOrDefault(key, 0) - value
                }
                cur.remove(sums[i])
            }
        }
        dfs(arrayListOf(), 0, hashMapOf())
        return ans
    }
}