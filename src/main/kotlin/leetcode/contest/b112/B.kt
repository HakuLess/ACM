package leetcode.contest.b112

class SolutionB {
    fun checkStrings(s1: String, s2: String): Boolean {
        val a = HashMap<Char, Int>()
        val b = HashMap<Char, Int>()
        val c = HashMap<Char, Int>()
        val d = HashMap<Char, Int>()
        for (i in s1.indices) {
            val item = s1[i]
            if (i % 2 == 0) {
                a[item] = a.getOrDefault(item, 0) + 1
            } else {
                b[item] = b.getOrDefault(item, 0) + 1
            }
        }
        for (i in s2.indices) {
            val item = s2[i]
            if (i % 2 == 0) {
                c[item] = c.getOrDefault(item, 0) + 1
            } else {
                d[item] = d.getOrDefault(item, 0) + 1
            }
        }
        if (a.keys.size != c.keys.size || b.keys.size != d.keys.size) return false
        return a.keys.all {
            a[it] == c.getOrDefault(it, 0)
        } && b.keys.all {
            b[it] == d.getOrDefault(it, 0)
        }
    }
}