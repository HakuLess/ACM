package leetcode.contest.c264

class Solution5906 {
    fun countValidWords(sentence: String): Int {
        fun check(str: String): Boolean {
            var ans = true
            if (str.isEmpty()) {
                ans = false
            }
            if (('0'..'9').any { n -> n in str }) {
                ans = false
            }
            if ('-' in str) {
                if (str.count { it == '-' } > 1) ans = false
                else {
                    val index = str.indexOf('-')
                    if (index == 0 || index == str.lastIndex) ans = false
                    else {
                        if (!str[index - 1].isLetter()
                            || str[index - 1].isUpperCase()
                            || str[index + 1].isUpperCase()
                            || !str[index + 1].isLetter()
                        ) {
                            ans = false
                        }
                    }
                }
            }
            val arr = arrayOf('!', '.', ',')
            if (str.count { it in arr } > 1) {
                ans = false
            } else if (str.count { it in arr } == 1) {
                if (str.last() !in arr) {
                    ans = false
                }
            }
            return ans
        }
        return sentence.split(" ").count {
            check(it)
        }
    }
}