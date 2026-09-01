/**
 * SISTEMA DE PERSONAJES - DEMOSTRACIÓN DE BUILDER + DECORATOR
 * Enfoque: Clean Code, alta cohesión, inmutabilidad de creación y polimorfismo.
 */

// 1. INTERFAZ BASE: Define el contrato. Vital para el Principio de Inversión de Dependencias (DIP).
interface GameCharacter {
    String getStats();
    int attack();
    int defend();
}

// 2. PRODUCTO CONCRETO Y BUILDER (Patrón Builder)
class Hero implements GameCharacter {
    private final String name;
    private final String armor;
    private final String weapon;
    private final String baseSkill;
    private final int baseAttack;
    private final int baseDefense;

    // XP: El constructor es privado. Obligamos al uso del Builder para evitar estados inválidos.
    private Hero(HeroBuilder builder) {
        this.name = builder.name;
        this.armor = builder.armor;
        this.weapon = builder.weapon;
        this.baseSkill = builder.baseSkill;
        this.baseAttack = builder.baseAttack;
        this.baseDefense = builder.baseDefense;
    }

    @Override
    public String getStats() {
        return String.format("%s [Arma: %s, Armadura: %s, Habilidad: %s]", name, weapon, armor, baseSkill);
    }

    @Override
    public int attack() { return baseAttack; }

    @Override
    public int defend() { return baseDefense; }

    // Clase Builder estática. Aplica Fluency (metodos encadenados) para Clean Code.
    public static class HeroBuilder {
        private final String name;
        private String armor = "Ninguna";
        private String weapon = "Desarmado";
        private String baseSkill = "Ninguna";
        private int baseAttack = 5;
        private int baseDefense = 5;

        public HeroBuilder(String name) {
            this.name = name;
        }

        public HeroBuilder setArmor(String armor, int defensePoints) {
            this.armor = armor;
            this.baseDefense += defensePoints;
            return this;
        }

        public HeroBuilder setWeapon(String weapon, int attackPoints) {
            this.weapon = weapon;
            this.baseAttack += attackPoints;
            return this;
        }

        public HeroBuilder setSkill(String skill) {
            this.baseSkill = skill;
            return this;
        }

        public GameCharacter build() {
            // XP: Aquí podríamos validar invariantes antes de instanciar.
            return new Hero(this);
        }
    }
}

// 3. BASE DECORATOR (Patrón Decorator)
// Actúa como proxy, delegando la ejecución al objeto envuelto.
abstract class CharacterBuff implements GameCharacter {
    protected final GameCharacter wrappedCharacter;

    public CharacterBuff(GameCharacter character) {
        this.wrappedCharacter = character;
    }

    @Override
    public String getStats() { return wrappedCharacter.getStats(); }

    @Override
    public int attack() { return wrappedCharacter.attack(); }

    @Override
    public int defend() { return wrappedCharacter.defend(); }
}

// 4. DECORADORES CONCRETOS (Alineados a OCP: Nuevos poderes son nuevas clases)
class ShieldDecorator extends CharacterBuff {
    public ShieldDecorator(GameCharacter character) {
        super(character);
    }

    @Override
    public String getStats() {
        return super.getStats() + " + [Escudo de Hielo Activo]";
    }

    @Override
    public int defend() {
        return super.defend() + 50; // Bonificación masiva de defensa
    }
}

class SpeedDecorator extends CharacterBuff {
    public SpeedDecorator(GameCharacter character) {
        super(character);
    }

    @Override
    public String getStats() {
        return super.getStats() + " + [Velocidad Extra Activa]";
    }

    @Override
    public int attack() {
        return super.attack() + 20; // Bonificación de ataque por agilidad
    }
}

// 5. DEMOSTRACIÓN FUNCIONAL
public class CharacterSystem {
    public static void main(String[] args) {
        System.out.println("--- FASE 1: INICIO DE PARTIDA (PATRÓN BUILDER) ---");
        // El cliente (motor de juego) construye un objeto complejo de forma semántica y legible.
        GameCharacter warrior = new Hero.HeroBuilder("Guerrero Élite")
                .setArmor("Placas de Acero", 30)
                .setWeapon("Espada Bastarda", 45)
                .setSkill("Furia Implacable")
                .build();

        printCharacterStatus(warrior);

        System.out.println("\n--- FASE 2: EN COMBATE (PATRÓN DECORATOR) ---");
        // El personaje recoge un ítem de velocidad y un mago le lanza un escudo de hielo.
        // Envolvemos el objeto dinámicamente sin mutar la clase Hero original.
        GameCharacter buffedWarrior = new ShieldDecorator(new SpeedDecorator(warrior));
        
        printCharacterStatus(buffedWarrior);
    }

    private static void printCharacterStatus(GameCharacter character) {
        System.out.println("Descripción : " + character.getStats());
        System.out.println("Ataque Total: " + character.attack());
        System.out.println("Defensa Total: " + character.defend());
    }
}