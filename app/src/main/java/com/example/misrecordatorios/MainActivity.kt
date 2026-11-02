
package com.example.misrecordatorios

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.misrecordatorios.ui.theme.MisRecordatoriosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MisRecordatoriosTheme {
                PantallaRecordatorios()
            }
        }
    }
}

// LIST: datos simulados
val recordatorios = listOf(
    Recordatorio(
        id = 1,
        titulo = "Estudiar Kotlin",
        descripcion = "Completar la API 1",
        fecha = "01/11/2026",
        prioridad = Prioridad.ALTA,
        categoria = Categoria.ESTUDIO
    ),
    Recordatorio(
        id = 2,
        titulo = "Comprar alimentos",
        descripcion = "Ir al supermercado",
        fecha = "02/11/2026",
        prioridad = Prioridad.MEDIA,
        categoria = Categoria.PERSONAL,
        estado = EstadoRecordatorio.Completado
    ),
    Recordatorio(
        id = 3,
        titulo = "Reunion de trabajo",
        descripcion = "Revisar tareas pendientes",
        fecha = "03/11/2026",
        prioridad = Prioridad.BAJA,
        categoria = Categoria.TRABAJO
    )
)

// SET: categorias sin duplicados
val categoriasUnicas: Set<Categoria> =
    recordatorios.map { it.categoria }.toSet()

// MAP: colores asociados a categorias
val coloresCategorias = mapOf(
    Categoria.PERSONAL to Color(0xFF2196F3),
    Categoria.TRABAJO to Color(0xFFFF9800),
    Categoria.ESTUDIO to Color(0xFF4CAF50),
    Categoria.OTROS to Color.Gray
)

// LIST: filtros por estado y prioridad
val recordatoriosPendientes = recordatorios.filter {
    it.estado == EstadoRecordatorio.Pendiente
}

val recordatoriosAltaPrioridad = recordatorios.filter {
    it.prioridad == Prioridad.ALTA
}

@Composable
fun PantallaRecordatorios() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mis Recordatorios",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Total: ${recordatorios.size} | " +
                    "Pendientes: ${recordatoriosPendientes.size}"
        )

        Text(
            text = "Prioridad alta: " +
                    "${recordatoriosAltaPrioridad.size}"
        )

        Text(
            text = "Categorias: ${categoriasUnicas.size}"
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(recordatorios, key = { it.id }) { recordatorio ->

                val colorCategoria =
                    coloresCategorias[recordatorio.categoria]
                        ?: Color.Gray

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = recordatorio.titulo,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = "Categoria: ${recordatorio.categoria}",
                            color = colorCategoria
                        )

                        Text(
                            text = "Prioridad: ${recordatorio.prioridad}"
                        )

                        Text(
                            text = "Estado: " +
                                    when (recordatorio.estado) {
                                        EstadoRecordatorio.Pendiente ->
                                            "Pendiente"
                                        EstadoRecordatorio.Completado ->
                                            "Completado"
                                    }
                        )

                        recordatorio.descripcion?.let {
                            Text(text = it)
                        }

                        recordatorio.fecha?.let {
                            Text(text = "Fecha: $it")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaRecordatoriosPreview() {
    MisRecordatoriosTheme {
        PantallaRecordatorios()
    }
}
