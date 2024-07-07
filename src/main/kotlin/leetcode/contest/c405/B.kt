package leetcode.contest.c405

class SolutionB {
    fun validStrings(n: Int): List<String> {
        fun generateStrings(current: String, n: Int, result: MutableList<String>) {
            if (current.length == n) {
                result.add(current)
                return
            }
            // 可以总是添加 '1'
            generateStrings(current + '1', n, result)
            // 只有当当前字符串为空或最后一个字符不是 '0' 时才能添加 '0'
            if (current.isEmpty() || current.last() != '0') {
                generateStrings(current + '0', n, result)
            }
        }
        val result = mutableListOf<String>()
        generateStrings("", n, result)
        return result
    }
}