package leetcode.contest.c434

class SolutionD {
    fun supersequences(words: Array<String>): List<List<Int>> {
        // 初始化频率数组
        val freqs = Array(26) { 0 }

        // 遍历所有单词，统计每个字母的最大出现次数
        for (word in words) {
            val count = IntArray(26)
            for (char in word) {
                count[char - 'a']++
            }
            for (i in 0 until 26) {
                freqs[i] = maxOf(freqs[i], count[i])
            }
        }

        // 获取所有最短公共超序列的字母频率
        val result = mutableSetOf<List<Int>>()
        result.add(freqs.toList())  // 将数组加入集合，去重

        // 转换为二维数组形式
        val output = result.map { it.toList() }

        return output
    }
}