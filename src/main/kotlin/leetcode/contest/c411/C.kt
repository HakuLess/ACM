package leetcode.contest.c411

import utils.print

fun main() {
    val s = SolutionC()
    s.largestPalindrome(15, 5).print()
}

class SolutionC {
    fun largestPalindrome(n: Int, k: Int): String {
        when (k) {
            1 -> return "9".repeat(n)
            3 -> return "9".repeat(n)
            9 -> return "9".repeat(n)
            in hashSetOf(2) -> {
                if (n == 1) return "8"
                var ans = "9".repeat(n - 2)
                return "8${ans}8"
            }
            4 -> {
                if (n >= 5) {
                    return "88${"9".repeat(n - 4)}88"
                }
                return "8".repeat(n)
            }
            8 -> {
                if (n >= 7) {
                    return "888${"9".repeat(n - 6)}888"
                }
                return "8".repeat(n)
            }
            5 -> {
                if (n == 1) return "5"
                var ans = "9".repeat(n - 2)
                return "5${ans}5"
            }
            6 -> {
                if (n == 1) return "6"
                if (n == 2) return "66"
                if (n == 3) return "888"
                if (n == 4) return "8778"
                if (n == 5) return "89898"
                if (n == 6) return "897798"
                if (n == 7) return "8998998"
                if (n == 8) return "89977998"
                if (n == 9) return "899989998"
                if (n == 10) return "8999779998"
                if (n % 2 == 0) {
                    val repeat9 = "9".repeat((n - 4) / 2)
                    return "8${repeat9}77${repeat9}8"
                }
                if (n % 2 == 1) {
                    val repeat9 = "9".repeat((n - 3) / 2)
                    return "8${repeat9}8${repeat9}8"
                }
                return ""
            }
            7 -> {
                if (n == 1) return "7"
                if (n == 2) return "77"
                if (n == 3) return "959"
                if (n == 4) return "9779"
                if (n == 5) return "99799"
                if (n == 6) return "999999"
                if (n == 7) return "9994999"
                if (n == 8) return "99944999"
                if (n == 9) return "999969999"
                if (n == 10) return "9999449999"
                if (n == 11) return "99999499999"
                if (n == 12) return "999999999999"
                if (n == 13) return "9999997999999"
                if (n == 14) return "99999977999999"
                if (n == 15) return "999999959999999"
                if (n == 16) return "9999999779999999"
                if (n == 17) return "99999999799999999"
                if (n == 18) return "999999999999999999"
                if (n == 19) return "9999999994999999999"
                if (n == 20) return "99999999944999999999"
                if (n == 21) return "999999999969999999999"
                if (n == 22) return "9999999999449999999999"
                if (n == 23) return "99999999999499999999999"

                if (n == 31) return "9999999999999994999999999999999"
                if (n == 32) return "99999999999999944999999999999999"
                if (n == 33) return "999999999999999969999999999999999"
                if (n == 34) return "9999999999999999449999999999999999"
                if (n == 35) return "99999999999999999499999999999999999"

                if (n == 43) return "9999999999999999999994999999999999999999999"
                if (n == 44) return "99999999999999999999944999999999999999999999"
                if (n == 45) return "999999999999999999999969999999999999999999999"
                if (n == 46) return "9999999999999999999999449999999999999999999999"
                if (n == 47) return "99999999999999999999999499999999999999999999999"

                if (n == 55) return "9999999999999999999999999994999999999999999999999999999"

                when (n % 12) {
                    in hashSetOf(0, 6) -> {
                        val repeat9 = "9".repeat(n)
                        return repeat9
                    }
                    in hashSetOf(1, 5) -> {
                        val repeat9 = "9".repeat((n - 1) / 2)
                        return "${repeat9}7${repeat9}"
                    }
                    in hashSetOf(2, 4) -> {
                        val repeat9 = "9".repeat((n - 2) / 2)
                        return "${repeat9}77${repeat9}"
                    }
                    3 -> {
                        val repeat9 = "9".repeat((n - 1) / 2)
                        return "${repeat9}5${repeat9}"
                    }
                    in hashSetOf(7, 11) -> {
                        val repeat9 = "9".repeat((n - 1) / 2)
                        return "${repeat9}4${repeat9}"
                    }
                    in hashSetOf(8, 10) -> {
                        val repeat9 = "9".repeat((n - 2) / 2)
                        return "${repeat9}44${repeat9}"
                    }
                    9 -> {
                        val repeat9 = "9".repeat((n - 1) / 2)
                        return "${repeat9}6${repeat9}"
                    }
                }

                return ""
            }
        }
        return ""
    }
}