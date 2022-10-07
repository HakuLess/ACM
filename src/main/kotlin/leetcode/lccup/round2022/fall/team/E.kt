package leetcode.lccup.round2022.fall.team

import utils.print

fun main() {
    val s = SolutionE()
    // [[1,1],[2,1],[3,3],[3,5],[4,2],[5,1],[5,3],[5,5],[5,7],[5,9]]
    s.sandyLandManagement(5).print()
    s.sandyLandManagement(3).print()
    s.sandyLandManagement(2).print()
    s.sandyLandManagement(10).print()
}

class SolutionE {
    fun sandyLandManagement(size: Int): Array<IntArray> {
        val ans = ArrayList<IntArray>()
        ans.add(intArrayOf(1, 1))
        if (size == 1) return ans.toTypedArray()
        for (i in 1..(2 * size - 1) step 2) {
            ans.add(intArrayOf(size, i))
        }
        var step = 0
        for (j in size - 1 downTo 2) {
            if (step % 2 == 0) {
                if (step % 4 == 0) {
                    ans.add(intArrayOf(j, 2))
                } else {
                    ans.add(intArrayOf(j, 1))
                }
            } else {
                if (step % 4 == 1) {
                    for (k in 3..2 * j - 1 step 2) {
                        ans.add(intArrayOf(j, k))
                    }
                } else {
                    for (k in 1..2 * j - 1 step 2) {
                        ans.add(intArrayOf(j, k))
                    }
                }
            }
            step++
        }
        return ans.toTypedArray()
    }
}