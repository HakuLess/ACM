package leetcode.normal

class Solution299 {
    fun getHint(secret: String, guess: String): String {
        val arr = IntArray(10)
        var a = 0
        var b = 0
        for (i in secret.indices) {
            if (guess[i] == secret[i]) {
                a++
            } else {
                arr[secret[i] - '0']++
            }
        }

        for (i in secret.indices) {
            if (guess[i] != secret[i]) {
                if (arr[guess[i] - '0'] > 0) {
                    b++
                    arr[guess[i] - '0']--
                }
            }
        }

        return "${a}A${b}B"
    }
}