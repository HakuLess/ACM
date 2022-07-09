package leetcode.contest.b82

import utils.UFS
import utils.print

fun main() {
    val s = SolutionD()
    s.validSubarraySize(intArrayOf(1, 3, 4, 3, 1), 6).print()
    s.validSubarraySize(intArrayOf(6, 5, 6, 5, 8), 6).print()

}

class SolutionD {
    fun validSubarraySize(nums: IntArray, threshold: Int): Int {
        val n = nums.size
        val ids = IntRange(0, n - 1).toList().toTypedArray()
        // 坐标x 由大大小排序
        // 对id进行排序
        ids.sortBy { -nums[it] }

        // 并查集 从大到小塞入
        val ufs = UFS(nums.size)
        val seen = HashSet<Int>()
        ids.forEach { id ->
            seen.add(id)
            if (id + 1 in seen) {
                ufs.union(id, id + 1)
            }
            if (id - 1 in seen) {
                ufs.union(id, id - 1)
            }
            val cnt = ufs.sz[ufs.find(id)]
            if (nums[id] > threshold.toDouble() / cnt) {
                return cnt
            }
        }
        return -1
    }
}