package leetcode.normal

class Solution386 {
    fun lexicalOrder(n: Int): List<Int> {
        val ans = ArrayList<Int>()
        var cur = 1
        for (i in 0 until n) {
            ans.add(cur)
            if (cur * 10 <= n) {
                cur *= 10
            } else {
                while (cur % 10 == 9 || cur + 1 > n) {
                    cur /= 10
                }
                cur++
            }
        }
        return ans
    }

//    fun lexicalOrder(n: Int): List<Int> {
//        val ans = ArrayList<String>()
//        for (i in 1..n) {
//            ans.add("$i")
//        }
//        return ans.sorted().map { it.toInt() }
//    }
}