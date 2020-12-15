# PaintWar

## Pour lancer une partie en ligne (ne fonctionne pas en local):
- Modifier les configurations de GameConfig en fournissant l'ip local du server, l'ip publique ou hostname du server, et 3 ports forwardés et non utilisés.
- Lancer le main du serveur dans `com.paintwar.server.service.game.GamesManager`.
- Lancer le main du client dans `com.paintwar.client.controller.game.ClientGameManager` et fournissez les informations demandées.

## Pour lancer une partie local
Cloner la branch `gameSystem` et lancer les 2 main comme précédemment (il faudra modfier l'ip local du serveur dans `GamesManager`)

## Pour lancer le prototype des pages de l'accueil du jeu
Lancer le main dans `com.paintwar.client.view.MainWindow`.

