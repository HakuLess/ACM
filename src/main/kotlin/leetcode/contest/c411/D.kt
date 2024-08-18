package leetcode.contest.c411

class SolutionD {
    fun countKConstraintSubstrings(s: String, k: Int, queries: Array<IntArray>): LongArray {
        val results = LongArray(queries.size)

        for (index in queries.indices) {
            val (li, ri) = queries[index]
            var count0 = 0
            var count1 = 0
            var left = li
            var totalCount = 0L

            for (right in li..ri) {
                if (s[right] == '0') count0++ else count1++

                while (count0 > k && count1 > k) {
                    if (s[left] == '0') count0-- else count1--
                    left++
                }

                totalCount += (right - left + 1).toLong()
            }

            results[index] = totalCount
        }

        return results
    }
}