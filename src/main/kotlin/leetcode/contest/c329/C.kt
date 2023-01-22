package leetcode.contest.c329

fun main() {
    println("0 xor 0 ${0 xor 0}")
    println("1 xor 0 ${1 xor 0}")
    println("1 xor 1 ${1 xor 1}")

    println("0 or 0 ${0 or 0}")
    println("1 or 0 ${1 or 0}")
    println("1 or 1 ${1 or 1}")
}

class SolutionC {
    fun makeStringsEqual(s: String, target: String): Boolean {
        // 0 0 无变化 无意义
        // 0 1 变为 1 1 (将0变为1)
        // 1 1 变为 0 1 (将1变为0)
        if (s == target) return true
        if (s.all { it == '0' }) return false
        // 始终有1个1无法变为0
        if (target.all { it == '0' }) return false
        return true
    }
}