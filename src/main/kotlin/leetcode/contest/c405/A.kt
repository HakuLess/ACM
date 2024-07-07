package leetcode.contest.c405

class SolutionA {
    fun getEncryptedString(s: String, k: Int): String {
        // 获取字符串长度
        val n = s.length

        // 创建一个StringBuilder用于存储加密后的结果
        val encrypted = StringBuilder()

        // 遍历字符串中的每一个字符
        for (i in s.indices) {
            // 找到字符后面第 k 个字符的位置（需要考虑循环）
            val newPosition = (i + k) % n
            // 将该字符添加到加密后的字符串中
            encrypted.append(s[newPosition])
        }

        return encrypted.toString()
    }
}