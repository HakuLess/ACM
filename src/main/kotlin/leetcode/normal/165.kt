package leetcode.normal

class Solution165 {
    fun compareVersion(version1: String, version2: String): Int {
        val v1 = version1.split('.').map { it.toInt() }
        val v2 = version2.split('.').map { it.toInt() }
        for (i in 0 until maxOf(v1.size, v2.size)) {
            if (v1.getOrElse(i) { 0 } < v2.getOrElse(i) { 0 }) {
                return -1
            } else if (v1.getOrElse(i) { 0 } > v2.getOrElse(i) { 0 }) {
                return 1
            }
        }
        return 0
    }
}