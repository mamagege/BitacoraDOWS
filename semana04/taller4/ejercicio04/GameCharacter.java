package ejercicio04;

/**
 * Componente Base (Decorator / Target de Builder):
 * Define el contrato de un personaje del videojuego.
 */
public interface GameCharacter {
    String getName();
    String getCharacterClass();
    String getArmor();
    String getWeapon();
    String getSkill();
    int getAttackPower();
    int getDefensePower();
    String getActiveEffects();
    void attack();
}
