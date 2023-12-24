# Pokemon

### Sélection des Monstres
Au début de la partie, choisissez trois monstres parmi une liste disponible.

### Menu
À chaque tour, vous pouvez choisir entre trois actions : "Attaquer", "Utiliser un Objet" ou "Changer de Monstre".

### Attaquer
Si vous choisissez d'attaquer, sélectionnez une attaque de votre monstre. Les dégâts dépendent de la puissance de l'attaque et des caractéristiques des monstres.

### Utiliser un Objet
Vous avez la possibilité d'utiliser des potions pour soigner vos monstres, augmenter leurs points d'attaque, améliorer leurs points de vie, ou des éponges pour assécher le terrain.

### Changer de Monstre
Vous pouvez les changer pendant le combat.

### Fin du Combat
Le combat se termine lorsque tous les monstres d'un joueur n'ont plus de points de vie. Le joueur qui élimine tous les monstres adverses remporte la partie.

### Gestion des Objets et Monstres
En dehors des combats, vous pouvez gérer vos monstres et objets, et charger de nouveaux monstres et attaques depuis les fichiers texte (Veuillez cependant noter qu'il est nécessaire de spécifier le type d'un monstre avant la capacité).

## Code

### Compilation
```bash
javac $(find | grep -E "./.*\java")
```

### Execution
```bash
java Explorer
```

### Deletion
```bash
rm $(find | grep -E "./.*\class")
```