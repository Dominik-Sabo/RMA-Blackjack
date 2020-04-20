import kotlin.collections.ArrayList

open class Player(val name: String?) {
    var score: Int = 0
    var splitScore: Int = 0
    var splitFlag: Boolean = false
    var hand = ArrayList<Card>(0)
    var splitHand = ArrayList<Card>(0)

    fun draw(deck: Deck, hand: ArrayList<Card>): Unit{
        hand.add(deck.draw())
    }

    open fun chooseSplit(): Boolean{
        var choice: String?
        while(true) {
            println("You have drawn two cards of the same rank. Split? (y/n)")
            choice = readLine()
            if (choice.equals("y"))
            {
                splitFlag = true
                return true
            }
            else if (choice.equals("n")) return false
        }
    }

    open fun chooseAceScore(currentScore: Int): Int{
        if(currentScore > 10) return 1
        else {
            var choice: String?
            while (true) {
                println("You have an ace. 1 or 11?")
                choice = readLine()
                if (choice.equals("1")) {
                    return 1

                } else if (choice.equals("11")) {
                    return 11

                }
            }
        }
    }

    fun showHand(hand: ArrayList<Card>): Unit{
        hand.forEach(){
            println(it.toString())
        }
        println("\n")
    }

    fun calculateScore(hand: ArrayList<Card>): Int{
        var currentScore: Int = 0
        var aceNumber: Int = 0
        hand.forEach{
            when (it.value) {
                1 -> aceNumber++
                in 10..13 -> currentScore += 10
                else -> currentScore += it.value
            }
        }
        for (i in 0 until aceNumber) currentScore += chooseAceScore(currentScore)
        return currentScore
    }

    open fun chooseDraw(): Boolean{
        var choice: String?
        while(true) {
            println("Draw? (y/n)")
            choice = readLine()
            if (choice.equals("y")) return true
            else if (choice.equals("n")) return false
        }
    }

    fun showScore(): Unit{
        println("Current score: ${this.splitScore}\n")
    }

    fun setScore(): Unit{
        if(splitScore > score) score = splitScore
    }

    fun split(): Unit{
        splitHand.add(hand[1])
        hand.removeAt(1)
    }

    fun clear(): Unit{
        this.score = 0
        this.splitScore = 0
        this.hand.clear()
        this.splitHand.clear()
    }
}