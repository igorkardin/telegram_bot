package app.state

@Volatile
var state = State.OPEN

enum class State {
    OPEN, CALCULATOR
}
