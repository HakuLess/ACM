package leetcode.contest.c396

class SolutionC {
    fun minAnagramLength(s: String): Int {
        val n = s.length

        fun checkI(k: Int): Boolean {
            val set = HashSet<String>()
            for (i in s.indices step k) {
                val sub = s.substring(i, i + k).toCharArray().sorted()
                set.add(sub.joinToString())
                if (set.size > 1) return false
            }
            return true
        }

        for (i in 1..n) {
            if (n % i == 0) {
                if (checkI(i)) {
                    return i
                }
            }
        }

        return s.length
    }
}