package leetcode.contest.c272

class SolutionB {
    fun addSpaces(s: String, spaces: IntArray): String {
        val sb = StringBuilder(s)
        var offset = 0
        spaces.forEach {
            sb.insert(it + offset, ' ')
            offset++
        }
        return sb.toString()
    }
}