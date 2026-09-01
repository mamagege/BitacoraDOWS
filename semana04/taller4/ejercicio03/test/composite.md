# Patrón de Diseño: Composite

## ¿Qué es?

Es un patrón de diseño estructural que permite componer objetos en estructuras de árbol para representar jerarquías de "parte-todo".

## ¿Qué hace?

Permite a los clientes tratar a los objetos individuales (hojas) y a los compuestos (ramas o contenedores) de manera completamente uniforme a través de una interfaz común, ignorando si están interactuando con un elemento único o con un grupo complejo de elementos.

## ¿Cómo se implementa?

- **Component (Interfaz Común)**: Declara las operaciones comunes tanto para los elementos simples como para los complejos.
- **Leaf (Hoja)**: Representa los objetos finales de la jerarquía. No tiene hijos y define el comportamiento base.
- **Composite (Contenedor)**: Almacena componentes hijos (tanto hojas como otros compuestos), implementa las operaciones delegándolas recursivamente en sus hijos y gestiona adiciones o eliminaciones.

## ¿Cómo identificarlo (Cuándo usarlo y Code Smells que resuelve)?

**Code Smells**: Presencia masiva de sentencias de tipo `instanceof` (`if (item instanceof Directory)`) en la lógica de negocio para evaluar si un objeto es un contenedor o una hoja, duplicando código de recorrido y violando el polimorfismo.

**Uso ideal**: Cuando necesitas modelar estructuras jerárquicas en forma de árbol (sistemas de archivos, menús de aplicaciones, organizaciones empresariales, nodos HTML/DOM) y quieres que el código cliente trate de forma idéntica a nodos individuales y colecciones de nodos.

**Alineación con Clean Code y XP**: Aplica el Open/Closed Principle (OCP) porque puedes agregar nuevos tipos de hojas o compuestos sin alterar el código cliente existente. Reduce la complejidad cognitiva al eliminar bifurcaciones condicionales.

## Ejemplo 1: Sistema de Archivos (Archivos y Carpetas)

Este escenario clásico modela un disco duro donde una carpeta puede contener archivos u otras carpetas anidadas, calculando el tamaño total de forma recursiva.

```java
import java.util.ArrayList;
import java.util.List;

// 1. Component
interface FileSystemComponent {
    void showDetails(String indent);
    int getSize();
}

// 2. Leaf (Objeto individual)
class FileItem implements FileSystemComponent {
    private final String name;
    private final int size;

    public FileItem(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📄 Archivo: " + name + " (" + size + " KB)");
    }

    @Override
    public int getSize() {
        return size;
    }
}

// 3. Composite (Contenedor)
class DirectoryItem implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public DirectoryItem(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📁 Carpeta: " + name);
        for (FileSystemComponent child : children) {
            child.showDetails(indent + "  ");
        }
    }

    @Override
    public int getSize() {
        return children.stream().mapToInt(FileSystemComponent::getSize).sum();
    }
}

// Demostración
public class CompositeFileSystemDemo {
    public static void main(String[] args) {
        DirectoryItem root = new DirectoryItem("Root");
        root.add(new FileItem("config.json", 2));
        
        DirectoryItem src = new DirectoryItem("src");
        src.add(new FileItem("Main.java", 15));
        src.add(new FileItem("Utils.java", 8));
        
        root.add(src);

        root.showDetails("");
        System.out.println("Tamaño Total: " + root.getSize() + " KB");
    }
}
```

## Ejemplo 2: Menú de Navegación Jerárquico

Permite estructurar menús de una interfaz de usuario donde un menú principal contiene enlaces directos (hojas) o submenús desplegables (compuestos).

```java
import java.util.ArrayList;
import java.util.List;

// 1. Component
interface MenuComponent {
    void render();
}

// 2. Leaf
class MenuItem implements MenuComponent {
    private final String title;
    private final String url;

    public MenuItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public void render() {
        System.out.println("    [Enlace] " + title + " -> " + url);
    }
}

// 3. Composite
class MenuComposite implements MenuComponent {
    private final String title;
    private final List<MenuComponent> children = new ArrayList<>();

    public MenuComposite(String title) {
        this.title = title;
    }

    public void add(MenuComponent component) {
        children.add(component);
    }

    @Override
    public void render() {
        System.out.println("[Desplegable] " + title);
        for (MenuComponent child : children) {
            child.render();
        }
    }
}

// Demostración
public class CompositeMenuDemo {
    public static void main(String[] args) {
        MenuComposite mainMenu = new MenuComposite("Menú Principal");
        mainMenu.add(new MenuItem("Home", "/home"));

        MenuComposite adminMenu = new MenuComposite("Panel de Administración");
        adminMenu.add(new MenuItem("Usuarios", "/admin/users"));
        adminMenu.add(new MenuItem("Configuración", "/admin/settings"));

        mainMenu.add(adminMenu);
        mainMenu.render();
    }
}
```

## Ejemplo 3: Estructura Organizacional (Cálculo de Nómina)

Modela una empresa donde un departamento contiene empleados individuales u otros subdepartamentos, permitiendo calcular el presupuesto salarial total de forma transparente.

```java
import java.util.ArrayList;
import java.util.List;

// 1. Component
interface OrganizationComponent {
    double getSalary();
    void printRole();
}

// 2. Leaf
class IndividualContributor implements OrganizationComponent {
    private final String name;
    private final double salary;

    public IndividualContributor(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public double getSalary() { return salary; }

    @Override
    public void printRole() {
        System.out.println("  - Empleado: " + name + " (Salario: $" + salary + ")");
    }
}

// 3. Composite
class DepartmentNode implements OrganizationComponent {
    private final String name;
    private final List<OrganizationComponent> members = new ArrayList<>();

    public DepartmentNode(String name) {
        this.name = name;
    }

    public void add(OrganizationComponent member) {
        members.add(member);
    }

    @Override
    public double getSalary() {
        return members.stream().mapToDouble(OrganizationComponent::getSalary).sum();
    }

    @Override
    public void printRole() {
        System.out.println("[Departamento] " + name);
        for (OrganizationComponent member : members) {
            member.printRole();
        }
    }
}

// Demostración
public class CompositeOrganizationDemo {
    public static void main(String[] args) {
        DepartmentNode company = new DepartmentNode("Global Corp");
        
        DepartmentNode devTeam = new DepartmentNode("Desarrollo");
        devTeam.add(new IndividualContributor("Alice (Dev)", 4000));
        devTeam.add(new IndividualContributor("Bob (QA)", 3500));

        company.add(devTeam);
        company.add(new IndividualContributor("Charlie (HR)", 3000));

        company.printRole();
        System.out.println("Nómina Total de la Empresa: $" + company.getSalary());
    }
}
```
