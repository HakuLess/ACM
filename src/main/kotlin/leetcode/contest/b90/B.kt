package leetcode.contest.b90

class SolutionB {
    fun twoEditWords(queries: Array<String>, dictionary: Array<String>): List<String> {
        val ans = ArrayList<String>()
        for (i in queries.indices) {
            for (j in dictionary.indices) {
                var c = 0
                val a = queries[i]
                val b = dictionary[j]
                for (k in a.indices) {
                    if (a[k] != b[k]) {
                        c++
                    }
                }
                if (c <= 2) {
                    ans.add(a)
                    break
                }
            }
        }
        return ans
    }
}