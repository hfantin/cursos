package colecoesparte3

fun main() {
    val assistiramCursoAndroid = listOf("Alex", "Fran", "Gui", "Maria")
    val assistiramCursoKotlin = listOf("Alex", "Paulo", "Maria")
    //    banco.nomes.add("Alex")
    val assistiramAmbos = assistiramCursoKotlin + assistiramCursoAndroid
    println(assistiramAmbos.distinct())
}