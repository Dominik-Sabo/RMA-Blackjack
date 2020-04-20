class Deck{
    var cards = ArrayList<Card>(52)

    init {
        this.make()
    }

    private fun make(): Unit{
        cards.clear()
        for (j in 0..3) {
            for (i in 1..13) {
                when (j) {
                    0 -> cards.add(Card(i, "spades"))
                    1 -> cards.add(Card(i, "diamonds"))
                    2 -> cards.add(Card(i, "clubs"))
                    3 -> cards.add(Card(i, "hearts"))
                }
            }
        }
    }

    fun shuffle(): Unit{
        this.make()
        cards.shuffle()
    }

    fun draw(): Card{
        val card: Card = cards[0]
        cards.removeAt(0)
        return card
    }
}