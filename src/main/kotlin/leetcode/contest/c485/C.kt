package leetcode.contest.c485

import java.util.TreeSet

class AuctionSystem() {

    private val bidsByItem = HashMap<Int, HashMap<Int, Int>>()

    private val orderedBids = HashMap<Int, TreeSet<Bid>>()

    data class Bid(val amount: Int, val userId: Int)

    private val comparator = Comparator<Bid> { a, b ->
        when {
            a.amount != b.amount -> b.amount - a.amount
            else -> b.userId - a.userId
        }
    }

    fun addBid(userId: Int, itemId: Int, bidAmount: Int) {

        val userMap = bidsByItem.getOrPut(itemId) { HashMap() }
        val set = orderedBids.getOrPut(itemId) { TreeSet(comparator) }

        if (userMap.containsKey(userId)) {
            val oldAmount = userMap[userId]!!
            set.remove(Bid(oldAmount, userId))
        }

        userMap[userId] = bidAmount
        set.add(Bid(bidAmount, userId))
    }

    fun updateBid(userId: Int, itemId: Int, newAmount: Int) {

        val userMap = bidsByItem[itemId]!!
        val set = orderedBids[itemId]!!

        val oldAmount = userMap[userId]!!
        set.remove(Bid(oldAmount, userId))

        userMap[userId] = newAmount
        set.add(Bid(newAmount, userId))
    }

    fun removeBid(userId: Int, itemId: Int) {

        val userMap = bidsByItem[itemId]!!
        val set = orderedBids[itemId]!!

        val amount = userMap[userId]!!
        set.remove(Bid(amount, userId))
        userMap.remove(userId)

        if (userMap.isEmpty()) {
            bidsByItem.remove(itemId)
            orderedBids.remove(itemId)
        }
    }

    fun getHighestBidder(itemId: Int): Int {
        val set = orderedBids[itemId] ?: return -1
        if (set.isEmpty()) return -1
        return set.first().userId
    }

}

/**
 * Your AuctionSystem object will be instantiated and called as such:
 * var obj = AuctionSystem()
 * obj.addBid(userId,itemId,bidAmount)
 * obj.updateBid(userId,itemId,newAmount)
 * obj.removeBid(userId,itemId)
 * var param_4 = obj.getHighestBidder(itemId)
 */