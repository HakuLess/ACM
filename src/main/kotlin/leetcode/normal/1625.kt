package leetcode.normal

class Solution1625 {
    fun findLexSmallestString(s: String, a: Int, b: Int): String {
        val visited = HashSet<String>()
        val queue: ArrayDeque<String> = ArrayDeque()
        queue.add(s)
        visited.add(s)
        var ans = s

        fun addOp(str: String): String {
            val chars = str.toCharArray()
            for (i in 1 until chars.size step 2) {
                val newDigit = (chars[i] - '0' + a) % 10
                chars[i] = ('0' + newDigit)
            }
            return String(chars)
        }

        fun rotateOp(str: String): String {
            val n = str.length
            return str.takeLast(b) + str.take(n - b)
        }

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            if (cur < ans) ans = cur

            val addRes = addOp(cur)
            if (visited.add(addRes)) queue.add(addRes)

            val rotRes = rotateOp(cur)
            if (visited.add(rotRes)) queue.add(rotRes)
        }

        return ans
    }
}