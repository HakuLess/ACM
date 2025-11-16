package leetcode.normal

class Solution3542 {
    fun minOperations(nums: IntArray): Int {
        val list = mutableListOf<Int>()
        var res = 0
        for (a in nums) {
            while (list.isNotEmpty() && list.last() > a) {
                list.removeAt(list.size - 1)
            }
            if (a == 0) continue
            if (list.isEmpty() || list.last() < a) {
                res++
                list.add(a)
            }
        }
        return res
    }
}