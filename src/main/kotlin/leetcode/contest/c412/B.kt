package leetcode.contest.c412

class SolutionB {
    fun countPairs(nums: IntArray): Int {
        fun almostEqual(a: String, b: String): Boolean {
            val len = maxOf(a.length, b.length) + 2
            val diffA = ArrayList<Char>()
            val diffB = ArrayList<Char>()
            for (i in 0 until len) {
                val tmpA = a.getOrElse(a.lastIndex - i) { '0' }
                val tmpB = b.getOrElse(b.lastIndex - i) { '0' }
                if (tmpA != tmpB) {
                    diffA.add(tmpA)
                    diffB.add(tmpB)
                }
            }
            if (diffA.isEmpty()) return true
            if (diffA.size == 2 && diffA[0] == diffB[1] && diffA[1] == diffB[0]) return true
            return false
        }

        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                val a = nums[i].toString()
                val b = nums[j].toString()
                if (almostEqual(a, b)) {
                    ans++
                }
            }
        }
        return ans
    }
}