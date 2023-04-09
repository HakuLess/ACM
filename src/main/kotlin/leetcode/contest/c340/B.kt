package leetcode.contest.c340

import utils.print

fun main() {
    val s = SolutionB()
    // 5, 0, 3, 4, 0
    s.distance(intArrayOf(1, 3, 1, 1, 2)).print()
}

class SolutionB {
    fun distance(nums: IntArray): LongArray {
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in nums.indices) {
            if (nums[i] !in map.keys) {
                map[nums[i]] = arrayListOf()
            }
            map[nums[i]]!!.add(i)
        }

        val ans = LongArray(nums.size)

        for (key in map.keys) {
            val cur = map[key]!!
            val l = LongArray(cur.size)
            var pre = 0L
            var c = 0
            for (i in cur.indices) {
                if (i - 1 in cur.indices) {
                    pre += c * (cur[i] - cur[i - 1])
                }
                l[i] += pre
                c++
            }
            pre = 0L
            c = 0
            for (i in cur.indices.reversed()) {
                if (i + 1 in cur.indices) {
                    pre += c * (cur[i + 1] - cur[i])
                }
                l[i] += pre
                c++
            }

            for (i in cur.indices) {
                ans[cur[i]] = l[i]
            }
        }

        return ans
    }
}