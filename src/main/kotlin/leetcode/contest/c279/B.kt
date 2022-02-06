package leetcode.contest.c279

class SolutionB {
    fun smallestNumber(num: Long): Long {
        if (num == 0L) return num
        val set = ArrayList<Int>()
        val str = num.toString()
        if (num < 0) {
            for (i in str.indices) {
                if (i == 0) continue
                set.add(str[i] - '0')
            }
            return -set.sortedDescending().joinToString("").toLong()
        }
        if (num > 0) {
            for (i in str.indices) {
                set.add(str[i] - '0')
            }
            set.sort()
            val c0 = set.count { it == 0 }
            val ans = set.sorted().joinToString("").trimStart('0')
            val arr = ArrayList<Char>()
            arr.addAll(ans.toCharArray().toList())
            repeat(c0) {
                arr.add(1, '0')
            }
            return arr.joinToString("").toLong()
        }
        return 0L
    }
}