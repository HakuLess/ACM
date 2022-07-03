package leetcode.contest.c300

class SolutionA {
    fun decodeMessage(key: String, message: String): String {
        val map = HashMap<Char, Char>()
        var c = 'a'
        key.forEach {
            if (it != ' ' && it !in map.keys) {
                map[it] = c
                c++
            }
        }
        return message.map { if (it == ' ') ' ' else map[it] }.joinToString("")
    }
}