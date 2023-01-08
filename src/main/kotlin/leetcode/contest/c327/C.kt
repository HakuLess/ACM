package leetcode.contest.c327

class SolutionC {
    fun isItPossible(word1: String, word2: String): Boolean {
        val a = HashMap<Char, Int>()
        val b = HashMap<Char, Int>()
        word1.forEach {
            a[it] = a.getOrDefault(it, 0) + 1
        }
        word2.forEach {
            b[it] = b.getOrDefault(it, 0) + 1
        }
        for (i in 'a'..'z') {
            for (j in 'a'..'z') {
                val ca = a.clone() as HashMap<Char, Int>
                val cb = b.clone() as HashMap<Char, Int>
                if (i in ca.keys && j in cb.keys) {
                    if (ca[i] == 1) {
                        ca.remove(i)
                    }
                    ca[j] = ca.getOrDefault(j, 0) + 1
                    if (cb[j] == 1) {
                        cb.remove(j)
                    }
                    cb[i] = cb.getOrDefault(i, 0) + 1

                    if (ca.keys.size == cb.keys.size) {
                        return true
                    }
                }
            }
        }
        return false
    }
}