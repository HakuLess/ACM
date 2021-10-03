package leetcode.normal

class Solution1436 {
    fun destCity(paths: List<List<String>>): String {
        val f = paths.map { it[0] }
        return paths.first { it[1] !in f }[1]
    }
}