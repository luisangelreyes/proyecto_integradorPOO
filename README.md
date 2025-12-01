# proyecto_integradorPOO
# Menú Principal
Al ejecutar el sistema, se mostrará el siguiente menú:

=== SISTEMA DE TICKETING LAB LIS ===

--- MENÚ PRINCIPAL ---
1. Reportar incidencia
2. Ver todas las incidencias
3. Asignar técnico
4. Cambiar estado
5. Ver reporte
6. Ver detalles de incidencia
0. Salir

# Flujo de Trabajo Típico
Reportar una Incidencia
   
1. Seleccionar "1. Reportar incidencia"
2. Elegir usuario que reporta
3. Seleccionar equipo afectado
4. Ingresar descripción del problema
5. Seleccionar tipo (Hardware/Software/Red)
6. Asignar prioridad (Alta/Media/Baja)
2. Gestionar como Técnico

 Seleccionar "3. Asignar técnico"
1. Ingresar ID de incidencia
2. Seleccionar técnico disponible
3. Usar "4. Cambiar estado" para actualizar progreso

Consultar Información
- "2. Ver todas las incidencias": Lista completa
- "5. Ver reporte": Estadísticas del sistema
- "6. Ver detalles": Información completa de una incidencia

# El sistema maneja tres tipos de usuarios:

Tipo	Identificación	Ejemplo
Estudiante	Matrícula	S21010001
Maestro	Número de personal	MP12345
Técnico	Número de personal	TP67890

# Ejemplo de Reporte Generado
=== REPORTE DEL SISTEMA ===

Total de incidentes: 3

Por estado:
- ABIERTA: 1
- EN_PROGRESO: 1
- RESUELTA: 1
- CERRADA: 0

Por tipo:
- HARDWARE: 2
- SOFTWARE: 1
- RED: 0

# El sistema incluye datos de prueba pre-cargados

Usuarios:
Estudiante - S21010001

Estudiante - S21010002

Maestro - MP12345

Técnico - TP67890

Técnico - TP67891

Equipos:
PC-01 (Laboratorio LIS)

PC-02 (Laboratorio LIS)

PC-03 (Laboratorio LIS)
