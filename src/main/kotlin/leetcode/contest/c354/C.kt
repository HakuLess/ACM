package leetcode.contest.c354

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumIndex(listOf(1, 1, 1, 2)).print()
}

class SolutionC {
    fun minimumIndex(nums: List<Int>): Int {
        if (nums.size <= 1) return -1

        val n = nums.size
        val map = HashMap<Int, Int>()

        fun getMain(): Int {
            var ans = -1
            nums.forEach {
                map[it] = map.getOrDefault(it, 0) + 1
                if (map[it]!! > n / 2) {
                    ans = it
                }
            }
            return ans
        }

        val main = getMain()
        var l = 0
        var r = n
        var lmain = 0
        var rmain = map[main]!!

        for (i in nums.indices) {
            l++
            r--
            if (nums[i] == main) {
                lmain++
                rmain--
            }
//            println("$i with $lmain in $l  $rmain in $r")
            if (lmain * 2 > l && rmain * 2 > r) {
                return i
            }
        }

        return -1
    }
}