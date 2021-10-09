package leetcode.normal

class Solution187 {
    fun findRepeatedDnaSequences(s: String): List<String> {
        val sb = ArrayList<Char>()
        val map = HashMap<String, Int>()
        for (i in s.indices) {
            sb.add(s[i])
            if (sb.size > 10) sb.removeAt(0)
            if (sb.size == 10) {
                val key = sb.joinToString("")
                map[key] = map.getOrDefault(key, 0) + 1
            }
        }
        return map.filter { it.value > 1 }.keys.toList()
    }
}