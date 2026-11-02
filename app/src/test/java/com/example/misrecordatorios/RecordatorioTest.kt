package com.example.misrecordatorios

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class RecordatorioTest {

    @Test
    fun tituloVacioDebeSerRechazado() {
        assertThrows(IllegalArgumentException::class.java) {
            Recordatorio(
                id = 1,
                titulo = "",
                prioridad = Prioridad.ALTA,
                categoria = Categoria.ESTUDIO
            )
        }
    }

    @Test
    fun idInvalidoDebeSerRechazado() {
        assertThrows(IllegalArgumentException::class.java) {
            Recordatorio(
                id = 0,
                titulo = "Estudiar Kotlin",
                prioridad = Prioridad.MEDIA,
                categoria = Categoria.ESTUDIO
            )
        }
    }

    @Test
    fun recordatorioValidoDebeCrearse() {
        val recordatorio = Recordatorio(
            id = 1,
            titulo = "Estudiar Kotlin",
            prioridad = Prioridad.ALTA,
            categoria = Categoria.ESTUDIO
        )

        assertEquals("Estudiar Kotlin", recordatorio.titulo)
        assertEquals(
            EstadoRecordatorio.Pendiente,
            recordatorio.estado
        )
    }
}
