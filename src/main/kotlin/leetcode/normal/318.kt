package leetcode.normal

class Solution318 {
    fun maxProduct(words: Array<String>): Int {
        var ans = 0
        val set = words.map { it.toSet() }
        for (i in 0 until words.lastIndex) {
            for (j in i + 1 until words.size) {
                if (('a'..'z').all {
                        !(it in set[i] && it in set[j])
                    }) {
                    ans = maxOf(ans, words[i].length * words[j].length)
                }
            }
        }
        return ans
    }
}