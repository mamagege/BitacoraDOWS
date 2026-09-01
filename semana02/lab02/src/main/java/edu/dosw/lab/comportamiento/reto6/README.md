# Reto #6: Sala de Urgencias

## 1. Patrón de Diseño
* **Categoría:** Comportamiento
* **Patrón Utilizado:** **Chain of Responsibility**

---

## 2. Justificación
La sala de urgencias del *Hospital San Rafael* atiende pacientes con dolencias de diferente gravedad (`Leve`, `Moderado`, `Grave`, `Crítico`). Cada profesional médico tiene autorización y competencia para atender un nivel específico de gravedad. Si un profesional no puede atender un caso, este debe ser remitido automáticamente al siguiente eslabón en la cadena de mando. Si ningún profesional disponible puede atenderlo (ej. nivel `Crítico`), el paciente se marca como remitido a otra institución.

El patrón **Chain of Responsibility** fue elegido porque:
1. **Desacopla al emisor de la solicitud (paciente/recepción) de sus posibles receptores (profesionales médicos):** La recepción solo envía el paciente al primer eslabón de la cadena sin necesitar saber quién lo atenderá finalmente.
2. **Facilita la reconfiguración y extensión de la cadena (OCP/SRP):** Se pueden insertar nuevos niveles de atención (ej. *Triage*, *Especialista Quirúrgico*) o cambiar el orden jerárquico sin afectar la lógica de los demás manejadores.
3. **Manejo controlado de casos no atendidos:** Si la solicitud llega al final de la cadena sin ser procesada, se gestiona uniformemente la remisión externa.

---

## 3. Aplicación y Estructura de Clases

| Clase / Archivo | Rol en el Patrón / Sistema |
| :--- | :--- |
| [`ManejadorAtencion.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/ManejadorAtencion.java) | **Handler Base:** Declara el método `setSiguiente()` para encadenar receptores y la lógica común de delegación en `atender(Paciente)`. |
| [`EnfermeroHandler.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/EnfermeroHandler.java) | **Concrete Handler:** Procesa dolencias de nivel **Leve** (prioridad máxima Baja - 1). |
| [`MedicoGeneralHandler.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/MedicoGeneralHandler.java) | **Concrete Handler:** Procesa dolencias de nivel **Moderado** (prioridad máxima Media - 2). |
| [`EspecialistaHandler.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/EspecialistaHandler.java) | **Concrete Handler:** Procesa dolencias de nivel **Grave** (prioridad máxima Alta - 3). |
| [`Paciente.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/Paciente.java) | **Request Model:** Encapsula el síntoma, nivel de gravedad, prioridad, profesional que lo atendió y bandera de remisión. |
| [`NivelGravedad.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/NivelGravedad.java) | **Domain Enum:** Define `LEVE`, `MODERADO`, `GRAVE`, `CRITICO`. |
| [`Prioridad.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/Prioridad.java) | **Domain Enum:** Define `BAJA(1)`, `MEDIA(2)`, `ALTA(3)` con sus valores numéricos asociados. |
| [`Reto6SalaUrgencias.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/Reto6SalaUrgencias.java) | **Client / Presentation:** Configura la cadena, procesa los pacientes y genera estadísticas con Streams. |

---

## 4. Uso de Streams
En [`Reto6SalaUrgencias.java`](file:///src/main/java/edu/dosw/lab/comportamiento/reto6/Reto6SalaUrgencias.java), la generación de estadísticas clínicas (conteo por nivel, cantidad de remitidos y promedio de prioridad de pacientes atendidos) se realiza usando Java Streams:
```java
// Conteo de atendidos por gravedad
long leves = pacientes.stream().filter(p -> p.getGravedad() == NivelGravedad.LEVE && !p.isRemitido()).count();
long moderados = pacientes.stream().filter(p -> p.getGravedad() == NivelGravedad.MODERADO && !p.isRemitido()).count();
long graves = pacientes.stream().filter(p -> p.getGravedad() == NivelGravedad.GRAVE && !p.isRemitido()).count();

// Cantidad de remitidos a otra institución
long remitidos = pacientes.stream().filter(Paciente::isRemitido).count();

// Promedio de prioridad de los atendidos
double promPrioridad = pacientes.stream()
                                .filter(p -> !p.isRemitido())
                                .mapToInt(p -> p.getPrioridad().getNivel())
                                .average()
                                .orElse(0.0);
```

---

## 5. Cómo Usarlo (Ejemplo de Código)

```java
// 1. Construir los eslabones y configurar la cadena de responsabilidad
ManejadorAtencion enfermero = new EnfermeroHandler();
ManejadorAtencion medico = new MedicoGeneralHandler();
ManejadorAtencion especialista = new EspecialistaHandler();

enfermero.setSiguiente(medico);
medico.setSiguiente(especialista);

// 2. Procesar pacientes por la cadena
Paciente p1 = new Paciente("Dolor de garganta", NivelGravedad.LEVE, Prioridad.BAJA);
Paciente p2 = new Paciente("Paro cardíaco", NivelGravedad.CRITICO, Prioridad.ALTA);

enfermero.atender(p1); // Atendido por Enfermero
enfermero.atender(p2); // No atendido -> Marcado como remitido a otra institución
```
