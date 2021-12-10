package leetcode.normal

class Solution748 {
    fun shortestCompletingWord(licensePlate: String, words: Array<String>): String {
        val arr = IntArray(26)
        licensePlate.map { it.toLowerCase() }.forEach {
            if (it in 'a'..'z') {
                arr[it - 'a']++
            }
        }
        return words.filter { str ->
            val cmp = IntArray(26)
            str.map { cmp[it - 'a']++ }
            ('a'..'z').all {
                cmp[it - 'a'] >= arr[it - 'a']
            }
        }.sortedBy { it.length }.first()
    }
}