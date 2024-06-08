class LightingOnCommand(
    private val lighting: Lighting,
): Command {
    override fun execute() {
        lighting.turnOn()
    }
}