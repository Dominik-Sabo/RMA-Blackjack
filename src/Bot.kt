class Bot(name: String) : Player(name) {

    override fun chooseSplit(): Boolean{
        return false
    }

    override fun chooseAceScore(currentScore: Int): Int {
        return if (currentScore > 10) 1
        else 11
    }

    override fun chooseDraw(): Boolean {
        println("(...)\n")
        readLine()
        return this.splitScore <= 16
    }
}