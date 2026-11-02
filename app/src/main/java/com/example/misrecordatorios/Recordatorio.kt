
package com.example.misrecordatorios

data class Recordatorio(
    val id: Int,
    val titulo: String,
    val descripcion: String? = null,
    val fecha: String? = null,
    val prioridad: Prioridad,
    val categoria: Categoria,
    val estado: EstadoRecordatorio = EstadoRecordatorio.Pendiente
) {
    init {
        require(id > 0) {
            "El ID debe ser mayor que cero"
        }
        require(titulo.isNotBlank()) {
            "El titulo no puede estar vacio"
        }
    }
}

enum class Prioridad {
    ALTA,
    MEDIA,
    BAJA
}

enum class Categoria {
    PERSONAL,
    TRABAJO,
    ESTUDIO,
    OTROS
}

sealed class EstadoRecordatorio {
    data object Pendiente : EstadoRecordatorio()
    data object Completado : EstadoRecordatorio()
}
