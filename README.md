# Data Mart – Analyse de l'activité des représentants

## 📌 Description

Ce projet consiste à concevoir un **Data Mart** (schéma en étoile) permettant
d'analyser l'activité des représentants (vendeurs) d'une entreprise de vente
d'ordinateurs.

L'objectif est de répondre aux besoins du chef d'entreprise :
- Les vendeurs font-ils leur travail ?
- Quelle est leur zone de couverture ?
- Où sont-ils les moins efficaces ?
- Quelle est la moyenne des ventes par représentant ?

Le projet inclut également une proposition de **schéma en flocon** à partir
du schéma en étoile initial.

---

## 🎯 Objectifs d'analyse

- Suivi de l'activité des vendeurs (ventes, promesses de ventes, visites)
- Analyse de la couverture géographique
- Détection des zones de faible efficacité
- Calcul de la moyenne des ventes par représentant
- Mesure du rendement (ventes / km parcourus, ventes / frais engagés)

---

## 🗂️ Sources de données

| Source | Informations |
|---|---|
| Système RH | Identité, région, équipe, ancienneté des vendeurs |
| Système de gestion des ventes | Ventes, promesses de ventes, clients, produits |
| Feuilles de route | Kilomètres, litres d'essence, frais de voyage, visites |

---

## 🧩 Modélisation

### Table de faits : `Fait_Activite_Vendeur`

| Colonne | Type | Rôle |
|---|---|---|
| id_temps | FK | → Dim_Temps |
| id_vendeur | FK | → Dim_Vendeur |
| id_geo | FK | → Dim_Geographie |
| id_produit | FK | → Dim_Produit |
| id_client | FK | → Dim_Client |
| montant_vente | DECIMAL | Mesure |
| nb_ventes | INT | Mesure |
| promesses_ventes | DECIMAL | Mesure |
| km_parcourus | DECIMAL | Mesure |
| litres_essence | DECIMAL | Mesure |
| frais_voyage | DECIMAL | Mesure |
| nb_visites | INT | Mesure |

### Dimensions

- **Dim_Temps** : id_temps, jour, mois, trimestre, année
- **Dim_Vendeur** : id_vendeur, nom, prénom, région, équipe, date_embauche, manager
- **Dim_Geographie** : id_geo, ville, région, zone_couverture, pays
- **Dim_Produit** : id_produit, modèle, catégorie, gamme, prix_unitaire
- **Dim_Client** : id_client, nom, type_client, secteur

### Schéma en étoile
        Dim_Temps        Dim_Vendeur
             \              /
              \            /
   Dim_Produit — Fait_Activite_Vendeur — Dim_Client
              /            \
             /              \
     Dim_Geographie     (mesures)

### Schéma en flocon

Le schéma en flocon normalise certaines dimensions en sous-dimensions :

- **Dim_Produit** → `Dim_Categorie` (id_categorie, libellé, gamme)
- **Dim_Geographie** → `Dim_Region` (id_region, nom_region, pays)
- **Dim_Vendeur** → `Dim_Equipe` (id_equipe, nom_equipe, manager)
Dim_Categorie — Dim_Produit —┐
                              │
Dim_Region — Dim_Geographie — Fait — Dim_Vendeur — Dim_Equipe
                              │
                         Dim_Temps, Dim_Client

**Utilité du schéma en flocon :**
- Réduire la redondance des données (hiérarchies : ville → région → pays)
- Faciliter la maintenance des dimensions
- Partager des attributs entre plusieurs dimensions
- ⚠️ Inconvénient : plus de jointures → requêtes plus lentes

---

## 📁 Structure du projet
data-mart-representants/
│
├── data/
│ ├── ventes.csv
│ ├── vendeurs.csv
│ └── feuilles_route.csv
│
├── sql/
│ ├── schema_etoile.sql
│ ├── schema_flocon.sql
│ └── requetes_analyse.sql
│
├── docs/
│ ├── modele_etoile.png
│ ├── modele_flocon.png
│ └── capture_environnement.png
│
└── README.md

---

## ⚙️ Environnement technique

- **SGBD** : PostgreSQL 16 (ou MySQL 8)
- **Outil BI** : Power BI / Tableau / Metabase
- **Langage** : SQL
- **Versionnage** : Git / GitHub

---

## 🚀 Installation

1. Cloner le dépôt :
```bash
git clone https://github.com/<utilisateur>/data-mart-representants.git
cd data-mart-representants
2. Créer la base de données :
psql -U postgres -c "CREATE DATABASE data_mart_vendeurs;"

3. Exécuter les scripts SQL :
psql -U postgres -d data_mart_vendeurs -f sql/schema_etoile.sql
psql -U postgres -d data_mart_vendeurs -f sql/schema_flocon.sql

4. Charger les données (optionnel) :
psql -U postgres -d data_mart_vendeurs -c "\copy Dim_Vendeur FROM 'data/vendeurs.csv' CSV HEADER;"

---

## 📊 Exemples de requêtes d'analyse

**Moyenne des ventes par vendeur :**
SELECT v.nom, v.prenom, AVG(f.montant_vente) AS moyenne_ventes
FROM Fait_Activite_Vendeur f
JOIN Dim_Vendeur v ON f.id_vendeur = v.id_vendeur
GROUP BY v.nom, v.prenom
ORDER BY moyenne_ventes DESC;

**Ventes par région :**
SELECT g.region, SUM(f.montant_vente) AS total_ventes
FROM Fait_Activite_Vendeur f
JOIN Dim_Geographie g ON f.id_geo = g.id_geo
GROUP BY g.region
ORDER BY total_ventes DESC;

**Rendement (ventes / km parcourus) :**
SELECT v.nom, SUM(f.montant_vente) / NULLIF(SUM(f.km_parcourus),0) AS rendement
FROM Fait_Activite_Vendeur f
JOIN Dim_Vendeur v ON f.id_vendeur = v.id_vendeur
GROUP BY v.nom
ORDER BY rendement DESC;

---

## 🖼️ Preuve de l'environnement fonctionnel

La capture d'écran `docs/capture_environnement.png` montre :
- La base de données créée dans le SGBD
- Les tables du schéma en étoile visibles
- Une requête exécutée avec un résultat
- L'outil BI connecté avec un graphique

---

## 👤 Auteur

- **mohamed amine trabelsi** – Module Data Warehouse / BI
- Année universitaire : 2026 – 2027

---

## 📄 Licence

Projet académique – libre d'utilisation à des fins pédagogiques.
