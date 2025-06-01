package leetcode.contest.c452

import utils.getPrimesBoolean

class SolutionD {
    fun maximumCount(nums: IntArray, queries: Array<IntArray>): IntArray {
        val n = nums.size
        val isPrime = getPrimesBoolean(100005)

        val primeId = HashMap<Int, Int>()
        var id = 0
        for (i in isPrime.indices) {
            if (isPrime[i]) {
                primeId[i] = id++
            }
        }

        fun countUniquePrimes(arr: IntArray): IntArray {
            val result = IntArray(arr.size)
            val seen = BooleanArray(id)
            var count = 0
            for (i in arr.indices) {
                val num = arr[i]
                val p = primeId[num]
                if (p != null && !seen[p]) {
                    seen[p] = true
                    count++
                }
                result[i] = count
            }
            return result
        }

        val ans = ArrayList<Int>()
        for (query in queries) {
            val (idx, value) = query
            nums[idx] = value
            val left = countUniquePrimes(nums)
            val right = countUniquePrimes(nums.reversedArray()).reversedArray()
            var tmp = 0
            for (k in 1 until n) {
                tmp = maxOf(tmp, left[k - 1] + right[k])
            }
            ans.add(tmp)
        }

        return ans.toIntArray()
    }
}