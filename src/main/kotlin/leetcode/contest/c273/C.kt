package leetcode.contest.c273

import utils.print

fun main() {
    val s = SolutionC()
    s.getDistances(intArrayOf(2, 1, 3, 1, 2, 3, 3)).print()
}

class SolutionC {
    fun getDistances(arr: IntArray): LongArray {
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in arr.indices) {
            map[arr[i]] = map.getOrDefault(arr[i], arrayListOf())
            map[arr[i]]!!.add(i)
        }
        val ans = LongArray(arr.size)
        map.forEach { t, u ->
            var left = 0L
            var c = 0L
            for (i in u.indices) {
                c++
                left += u[i]
                val tmp = u[i] * c - left
                ans[u[i]] += tmp
            }
            var right = 0L
            c = 0L
            for (i in u.indices.reversed()) {
                c++
                right += u[i]
                val tmp = u[i] * c - right
                ans[u[i]] -= tmp
            }
        }

        return ans
    }
}