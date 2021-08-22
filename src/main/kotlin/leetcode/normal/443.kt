package leetcode.normal

import utils.print

// 443. 压缩字符串
// 443. String Compression
fun main() {
    val s = Solution443()
    s.compress(charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c')).print()
}

class Solution443 {
    fun compress(chars: CharArray): Int {
        val sb = StringBuilder()
        var count = 0
        var char = chars[0]
        chars.forEach {
            if (char == it) {
                count++
            } else {
                sb.append(char)
                if (count != 1) sb.append(count)
                char = it
                count = 1
            }
        }
        sb.append(char)
        if (count != 1) sb.append(count)
        for (i in sb.indices) {
            chars[i] = sb[i]
        }
        return sb.length
    }
}