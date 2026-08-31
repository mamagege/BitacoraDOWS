# ⚡ Cheat Sheet: Comandos de Git y Emergencias

> **Módulo:** Referencia Rápida  
> **Propósito:** Tabla de comandos para consultas ultra-rápidas durante el examen y guías de resolución para escenarios de "emergencia".

---

## 🆘 Protocolos de Emergencia en Git

### 1. "Cometí un error en mi último commit pero aún no hago push"
```bash
# Cambiar solo el mensaje del commit
git commit --amend -m "nuevo mensaje correcto"

# Olvidé agregar un archivo al commit
git add archivo_olvidado.java
git commit --amend --no-edit
```

### 2. "Quiero deshacer commits recientes"
```bash
# Deshacer el commit pero MANTENER mis cambios en el editor (Safe)
git reset --soft HEAD~1

# Deshacer el commit y DESTRUIR los cambios por completo (Danger)
git reset --hard HEAD~1
```

### 3. "Tengo cambios a medias, necesito cambiar de rama pero no quiero hacer commit"
```bash
# Guardar cambios en un "borrador" temporal
git stash

# Ver mis borradores guardados
git stash list

# Recuperar mis cambios en la rama actual (y borrar el borrador)
git stash pop

# Descartar un stash sin aplicarlo
git stash drop
```

### 4. "Borré una rama por accidente o rompí el historial"
```bash
# Muestra un historial absoluto de CADA acción en el repo local (incluso commits huérfanos/borrados)
git reflog

# Una vez encuentras el hash (ej. a1b2c3d) del momento donde todo funcionaba:
git reset --hard a1b2c3d
```

---

## 📊 Tabla Resumen de Comandos

| Acción | Comando | Descripción |
| :--- | :--- | :--- |
| **Sincronizar** | `git fetch` | Descarga metadatos y ramas del remoto sin alterar tu código local. |
| **Sincronizar** | `git pull` | Descarga y fusiona (`fetch` + `merge`) el código del remoto. |
| **Sincronizar** | `git push origin <rama>` | Sube tus commits locales al repositorio remoto. |
| **Sincronizar** | `git push --force` | Sobreescribe el historial remoto con el tuyo (Usar solo si hiciste rebase). |
| **Inspección** | `git log --oneline -5` | Ver los últimos 5 commits de forma resumida en una sola línea. |
| **Inspección** | `git diff` | Ver cambios locales antes de agregarlos al staging (`git add`). |
| **Limpieza** | `git clean -fd` | Borra archivos y carpetas no rastreados (*untracked*) de tu directorio. |
| **Etiquetado** | `git tag -a v1.0 -m "V1"` | Crea una etiqueta (tag) anotada en el commit actual. |

---




