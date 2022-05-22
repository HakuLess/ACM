package leetcode.normal

class Solution953 {
    fun isAlienSorted(words: Array<String>, order: String): Boolean {
        val sorted = words.sortedBy {
            var v = ""
            it.map {
                v += 'a' + order.indexOf(it)
            }
            v
        }
        return sorted.joinToString() == words.joinToString()
    }
}