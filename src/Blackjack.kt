import kotlin.collections.ArrayList

class Blackjack() {
    val deck: Deck = Deck()
    var highScore: Int = 0
    var players = ArrayList<Player>(0)


    fun addPlayer(player: Player){
        players.add(player)
    }

    fun decideWinner(): Unit{
        var winner: Player = Player("win")
        var draw: Boolean = false
        players.forEach(){
            if(it.score > winner.score){
                winner = it
                draw = false
            } else if (it.score == winner.score) draw = true
        }
        if (draw) announceDraw()
        else announceWinner(winner)
    }

    fun announceWinner(player: Player): Unit{
        println("\n${player.name} wins!")
    }

    fun announceDraw(): Unit{
        println("\nThe game is a draw!")
    }

    fun showHands(): Unit{
        players.forEach{
            println(it.name)
            it.showHand(it.hand)
        }
    }

    fun play(): Unit {
        deck.shuffle()
        players.shuffle()
        deal()
        showHands()
        for (i in 0 until players.size) {
            println("\n${players[i].name}'s turn!")
            if (players[i].hand[0].value == players[i].hand[1].value) {
                if (players[i].chooseSplit()) players[i].split()
            } else if ((players[i].hand[0].value > 9 && players[i].hand[1].value == 1) || (players[i].hand[0].value == 1 && players[i].hand[1].value > 9)){
                blackjack(players[i])
                continue
            }
            playerTurn(i, players[i].hand)
            if (players[i].splitFlag){
                players[i].splitFlag = false
                playerTurn(i, players[i].splitHand)
                println("Final score: ${players[i].score}\n")
            }
        }
        decideWinner()
        players.forEach() {it.clear()}
        if(askPlayAgain()) play()
    }

    fun deal(): Unit{
        for (j in 0..1) {
            players.forEach{
                it.draw(deck, it.hand)
            }
        }
    }

    fun playerTurn(i: Int, hand: ArrayList<Card>): Unit{
        while(true) {
            players[i].showHand(hand)
            players[i].splitScore = players[i].calculateScore(hand)
            if (players[i].splitScore > 21) {
                bust(players[i])
                break
            }
            players[i].showScore()
            if (!players[i].chooseDraw()){
                players[i].setScore()
                break
            }
            players[i].draw(deck, hand)
        }
    }

    fun blackjack(player: Player): Unit{
        println("Blackjack!\n(...)\n")
        player.score = 21
        readLine()
    }

    fun bust(player: Player): Unit{
        println("Bust!\n(...)\n")
        player.splitScore = 0
        readLine()
    }

    fun askPlayAgain(): Boolean{
        var choice: String?
        while(true) {
            println("Play again? (y/n)")
            choice = readLine()
            if (choice.equals("y")) return true
            else if (choice.equals("n")) return false
        }
    }

}