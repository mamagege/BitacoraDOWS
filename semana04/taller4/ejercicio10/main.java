import java.util.Stack;

/**
 * EDITOR DE IMÁGENES - DEMOSTRACIÓN DE DECORATOR + COMMAND
 * Enfoque: Transformaciones inmutables (Decorator) y control transaccional (Command).
 */

// ==========================================
// 1. PATRÓN DECORATOR: La Imagen y sus Filtros
// ==========================================
interface Image {
    String render();
}

class BaseImage implements Image {
    @Override
    public String render() {
        return "[Imagen Original RGB]";
    }
}

abstract class ImageDecorator implements Image {
    protected final Image wrappedImage;

    public ImageDecorator(Image wrappedImage) {
        this.wrappedImage = wrappedImage;
    }
    
    @Override
    public String render() {
        return wrappedImage.render();
    }
}

class GrayscaleDecorator extends ImageDecorator {
    public GrayscaleDecorator(Image wrappedImage) { super(wrappedImage); }
    @Override
    public String render() { return super.render() + " -> (Filtro: Blanco y Negro)"; }
}

class SepiaDecorator extends ImageDecorator {
    public SepiaDecorator(Image wrappedImage) { super(wrappedImage); }
    @Override
    public String render() { return super.render() + " -> (Filtro: Sepia)"; }
}

class NoiseReductionDecorator extends ImageDecorator {
    public NoiseReductionDecorator(Image wrappedImage) { super(wrappedImage); }
    @Override
    public String render() { return super.render() + " -> (Filtro: Reducción de Ruido)"; }
}

// ==========================================
// 2. PATRÓN COMMAND: Operaciones Reversibles
// ==========================================
interface ImageCommand {
    void execute();
    void undo();
}

// XP: El comando guarda el 'previousState' para restaurar sin romper encapsulamiento.
class ApplyFilterCommand implements ImageCommand {
    private final ImageEditor editor;
    private final FilterType filterType;
    private Image previousState;

    public enum FilterType { GRAYSCALE, SEPIA, NOISE_REDUCTION }

    public ApplyFilterCommand(ImageEditor editor, FilterType filterType) {
        this.editor = editor;
        this.filterType = filterType;
    }

    @Override
    public void execute() {
        // Guardamos el estado previo antes de mutar
        this.previousState = editor.getCurrentImage();
        
        // Aplicamos el Decorator correspondiente
        Image newImage = switch (filterType) {
            case GRAYSCALE -> new GrayscaleDecorator(previousState);
            case SEPIA -> new SepiaDecorator(previousState);
            case NOISE_REDUCTION -> new NoiseReductionDecorator(previousState);
        };
        
        editor.setCurrentImage(newImage);
    }

    @Override
    public void undo() {
        // Restauramos el estado previo (quitando el wrapper)[cite: 2]
        editor.setCurrentImage(this.previousState);
    }
}

// ==========================================
// 3. CONTEXTO Y GESTIÓN DE HISTORIAL (Invoker)
// ==========================================
class ImageEditor {
    private Image currentImage;
    // XP: Dos stacks separados para manejar Undo y Redo[cite: 2].
    private final Stack<ImageCommand> undoStack = new Stack<>();
    private final Stack<ImageCommand> redoStack = new Stack<>();

    public ImageEditor(Image baseImage) {
        this.currentImage = baseImage;
    }

    public Image getCurrentImage() { return currentImage; }
    
    public void setCurrentImage(Image image) { this.currentImage = image; }

    public void applyFilter(ApplyFilterCommand.FilterType type) {
        ImageCommand command = new ApplyFilterCommand(this, type);
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // Al hacer una nueva acción, se limpia el futuro
        System.out.println("[ACCIÓN] Filtro " + type + " aplicado.");
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("[INFO] No hay acciones para deshacer.");
            return;
        }
        ImageCommand command = undoStack.pop();
        command.undo();
        redoStack.push(command);
        System.out.println("[ACCIÓN] Deshacer (Undo) ejecutado.");
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("[INFO] No hay acciones para rehacer.");
            return;
        }
        ImageCommand command = redoStack.pop();
        command.execute();
        undoStack.push(command);
        System.out.println("[ACCIÓN] Rehacer (Redo) ejecutado.");
    }

    public void display() {
        System.out.println("🖼️  Pantalla: " + currentImage.render());
    }
}

// ==========================================
// 4. DEMOSTRACIÓN FUNCIONAL
// ==========================================
public class ImageEditorSystem {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO EDITOR DE IMÁGENES ---");
        ImageEditor editor = new ImageEditor(new BaseImage());
        editor.display();

        System.out.println("\n--- APLICANDO FILTROS ACUMULATIVOS ---");
        editor.applyFilter(ApplyFilterCommand.FilterType.NOISE_REDUCTION);
        editor.applyFilter(ApplyFilterCommand.FilterType.SEPIA);
        editor.display();

        System.out.println("\n--- PROBANDO UNDO (DESHACER) ---");
        editor.undo(); // Debería quitar el Sepia
        editor.display();

        System.out.println("\n--- PROBANDO REDO (REHACER) ---");
        editor.redo(); // Debería volver a poner el Sepia
        editor.display();

        System.out.println("\n--- APLICANDO NUEVA ACCIÓN Y ROMPIENDO REDO ---");
        editor.applyFilter(ApplyFilterCommand.FilterType.GRAYSCALE);
        editor.display();
    }
}