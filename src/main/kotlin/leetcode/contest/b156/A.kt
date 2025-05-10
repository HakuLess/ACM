package leetcode.contest.b156

class SolutionA {
    fun maxFreqSum(s: String): Int {
        val mapA = HashMap<Char, Int>()
        val mapB = HashMap<Char, Int>()
        val a = hashSetOf('a', 'e', 'i', 'o', 'u')
        s.forEach {
            if (it in a) {
                mapA[it] = mapA.getOrDefault(it, 0) + 1
            } else {
                mapB[it] = mapB.getOrDefault(it, 0) + 1
            }
        }
        return (mapA.values.maxOrNull() ?: 0) + (mapB.values.maxOrNull() ?: 0)
    }
}