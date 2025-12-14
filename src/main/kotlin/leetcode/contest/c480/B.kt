package leetcode.contest.c480

class SolutionB {
    fun reverseWords(s: String): String {
        val v = hashSetOf('a', 'e', 'i', 'o', 'u')
        val words = s.split(' ')
        val cnt = words[0].count { it in v }

        val ans = ArrayList<String>()
        for (i in words.indices) {
            val it = words[i]
            if (i == 0) {
                ans.add(it)
                continue
            }
            if (it.count { it in v } == cnt) {
                ans.add(it.reversed())
            } else {
                ans.add(it)
            }
        }
        return ans.joinToString(" ")
    }
}