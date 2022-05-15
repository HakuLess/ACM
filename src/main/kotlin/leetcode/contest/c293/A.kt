package leetcode.contest.c293

class SolutionA {
    fun removeAnagrams(words: Array<String>): List<String> {
        val ans = ArrayList<String>()
        ans.add(words[0])
        for (i in 1 until words.size) {
            val a = words[i - 1].toCharArray().sorted().joinToString()
            val b = words[i].toCharArray().sorted().joinToString()
            if (a != b) {
                ans.add(words[i])
            }
        }
        return ans
    }
}