package leetcode.contest.c389

class SolutionC {
    fun minimumDeletions(word: String, k: Int): Int {
        val map = HashMap<Char, Int>()
        word.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        val l = map.values.sorted()
        var ans = Int.MAX_VALUE
        for (i in l.indices) {
            var tmp = 0
            val min = l[i]
            for (j in l.indices) {
                if (l[j] < min) {
                    tmp += l[j]
                } else {
                    tmp += maxOf(l[j] - min - k, 0)
                }
            }
            ans = minOf(ans, tmp)
        }
        return ans
    }
}