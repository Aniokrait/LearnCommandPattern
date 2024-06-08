class CommandInvoker {
    private var command: Command? = null

    fun setCommand(command: Command) {
        this.command = command
    }

    fun runCommand() {
        if (command == null) {
            throw CommandMustBeSetException("Command must be set.")
        }
        command?.execute()
    }
}