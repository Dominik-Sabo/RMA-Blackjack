class Card(val value: Int, val suit: String) {

    override fun toString(): String{
        return when(this.value){
            1 -> "Ace of ${this.suit}"
            11 -> "Jack of ${this.suit}"
            12 -> "Queen of ${this.suit}"
            13 -> "King of ${this.suit}"
            else -> "${this.value} of ${this.suit}"
        }
    }
}