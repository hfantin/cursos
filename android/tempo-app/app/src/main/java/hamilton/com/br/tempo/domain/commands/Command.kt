package hamilton.com.br.tempo.domain.commands

/**
 * Created by hamilton on 23/10/17.
 */
interface Command<out T>{
    fun execute() : T
}