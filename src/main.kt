
fun main() {
    var blackjack: Blackjack = Blackjack()

    var n = 1
    var choice: String?

    while(n<=7) {
        println("Add players (p - player, b - bot, n - play)")
        choice = readLine()
        if (choice.equals("p")){
            println("Type player name")
            blackjack.addPlayer(Player(readLine()))
        }
        else if (choice.equals("b")){
            blackjack.addPlayer(Bot("Bot $n"))
            println("Added Bot $n")
        }
        else if (choice.equals("n")){
            break
        }
        n++
    }

    blackjack.play()
}