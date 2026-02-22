package leetcode.contest.c490

class SolutionC {
    fun maximumXor(s: String, t: String): String {

        var ones = 0
        var zeros = 0
        for (c in t) {
            if (c == '1') ones++ else zeros++
        }

        val sb = StringBuilder()
        for (c in s) {
            if (c == '0') {
                if (ones > 0) {
                    sb.append('1')
                    ones--
                } else {
                    sb.append('0')
                    zeros--
                }
            } else {
                if (zeros > 0) {
                    sb.append('1')
                    zeros--
                } else {
                    sb.append('0')
                    ones--
                }
            }
        }

        return sb.toString()
    }
}