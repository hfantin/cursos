package br.com.alura.technews.database.dao

import androidx.room.*
import br.com.alura.technews.model.Noticia

@Dao
interface NoticiaDAO {

    @Query("SELECT * FROM Noticia ORDER BY id DESC")
    fun buscaTodos(): List<Noticia>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(noticia: Noticia)

    @Delete
    fun remove(noticia: Noticia)

    @Query("SELECT * FROM Noticia WHERE id = :id")
    fun buscaPorId(id: Long): Noticia?

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun salva(noticias: List<Noticia>)

}
