package leetcode.normal

class Solution500 {
    fun findWords(words: Array<String>): Array<String> {
        val row0 = arrayOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p')
        val row1 = arrayOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l')
        val row2 = arrayOf('z', 'x', 'c', 'v', 'b', 'n', 'm')

        return words.filter {
            it.toLowerCase().all { it in row0 } ||
                    it.toLowerCase().all { it in row1 } ||
                    it.toLowerCase().all { it in row2 }
        }.toTypedArray()
    }
}