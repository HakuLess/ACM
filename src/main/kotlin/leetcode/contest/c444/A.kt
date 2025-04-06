package leetcode.contest.c444

class SolutionA {
    fun minimumPairRemoval(nums: IntArray): Int {
        val list = ArrayList<Int>()
        list.addAll(nums.toTypedArray())

        var ans = 0

        fun isNonDecreasing(): Boolean {
            for (i in 1 until list.size) {
                if (list[i] < list[i - 1]) return false
            }
            return true
        }

        while (!isNonDecreasing()) {
            var minSum = Int.MAX_VALUE
            var idx = 0
            for (i in 0 until list.size - 1) {
                val s = list[i] + list[i + 1]
                if (s < minSum) {
                    minSum = s
                    idx = i
                }
            }
            list.removeAt(idx)
            list.removeAt(idx)
            list.add(idx, minSum)
            ans++
        }

        return ans
    }
}