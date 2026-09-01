package ejercicio4;

public interface CharacterBuilder {
    void setArmor(String type);
    void setWeapon(String type);
    void setSkill(String type);
    Character build();

}
