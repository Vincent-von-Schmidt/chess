package hwr.oop.board

class Board () {
    // piece kann null sein, da bei der Initierung des Bords, keine uebergeben werden

    private val fields: Map<Location, Field> = run { // mapt locations zu field
        val allFields = mutableMapOf<Location, Field>()
        for (x in 'a'..'h') {
            for (y in 1..8) {
                val loc = Location(x, y)
                allFields[loc] = Field(loc)
            }
        }
        allFields.toMap()
    }
}