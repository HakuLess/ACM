package leetcode.contest.c263

class Bank(val balance: LongArray) {

    fun transfer(account1: Int, account2: Int, money: Long): Boolean {
        if (account1 - 1 in balance.indices && account2 - 1 in balance.indices) {
            if (balance[account1 - 1] >= money) {
                balance[account1 - 1] -= money
                balance[account2 - 1] += money
                return true
            }
        }
        return false
    }

    fun deposit(account: Int, money: Long): Boolean {
        if (account - 1 in balance.indices) {
            balance[account - 1] += money
            return true
        }
        return false
    }

    fun withdraw(account: Int, money: Long): Boolean {
        if (account - 1 in balance.indices) {
            if (balance[account - 1] >= money) {
                balance[account - 1] -= money
                return true
            }
        }
        return false
    }

}

/**
 * Your Bank object will be instantiated and called as such:
 * var obj = Bank(balance)
 * var param_1 = obj.transfer(account1,account2,money)
 * var param_2 = obj.deposit(account,money)
 * var param_3 = obj.withdraw(account,money)
 */