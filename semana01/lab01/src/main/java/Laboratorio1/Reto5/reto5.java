package Reto5;

import com.sun.source.tree.Tree;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

import static Reto5.Funciones.*;
import static Reto5.GuerrerosHash.*;

public class reto5 {
    public static void main(String[] args) {

        HashSet<Integer> hashSet = (HashSet<Integer>) eliminarMultiplos(leerHashSet(),3);
        TreeSet<Integer> treeSet = new TreeSet<>(eliminarMultiplos(leerTreeSet(),5));

        TreeSet<Integer> setConjunto = new TreeSet<>(treeSet);
        setConjunto.addAll(hashSet);

        setConjunto.forEach(e -> System.out.println("Número en arena: " + e));






    }
}
