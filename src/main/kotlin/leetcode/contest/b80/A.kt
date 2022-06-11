package leetcode.contest.b80

class SolutionA {
    fun strongPasswordCheckerII(password: String): Boolean {
        val ans =  password.length >= 8 &&
                ('a'..'z').any { it in password } &&
                ('A'..'Z').any { it in password } &&
                ('0'..'9').any { it in password } &&
                "!@#\$%^&*()-+".toCharArray().any { it in password }
        if (!ans) return false
        for (i in 1 until password.length) {
            if (password[i] == password[i - 1]) return false
        }
        return true
    }
}