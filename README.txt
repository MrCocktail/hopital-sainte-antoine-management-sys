# 🏥 HSA - Hospital Management System (Hôpital Sainte-Antoine)

HSA est une application d'entreprise moderne et robuste dédiée à la gestion administrative, médicale et financière de l'**Hôpital Sainte-Antoine** situé à Port-au-Prince, Haïti. 

Le système est conçu avec une architecture multi-tiers conforme aux spécifications **Jakarta EE** (EJB, JPA, JSF) et s'exécute sur le serveur d'applications **WildFly**. L'interface utilisateur est entièrement propulsée par **Tailwind CSS** et optimisée pour l'administration RH et la comptabilité.

---

## 🚀 Fonctionnalités Principales

### 📊 1. Cockpit & Tableau de Bord (Dashboard)
* **Indicateurs clés de performance (KPI) dynamiques** connectés à la base de données : nombre de médecins praticiens actifs, patients enregistrés, et fiches de paie validées.
* **Répartition RH automatique** affichant le ratio en temps réel entre le corps médical et le personnel administratif sous forme de jauges graphiques.
* **Panneau d'actions rapides** permettant de naviguer instantanément vers les fonctionnalités critiques.

### 👥 2. Gestion du Personnel & Rôles
* **Fiches Employés Standard** : Gestion complète du personnel administratif (salaire forfaitaire mensuel de référence basé sur 8h/jour ouvré).
* **Fiches Médecins Praticiens** : Suivi spécifique basé sur une tarification horaire contractuelle appliquée aux relevés de présences.

### 🕒 3. Suivi du Temps de Garde
* Module de saisie et de suivi des feuilles de temps (`HeureFournies`) enregistrant les pointages avec heure de début et heure de fin.
* Algorithme d'extraction sécurisé gérant les passages de gardes de nuit sur le lendemain.

### 💳 4. Gestion du Payroll & Clôture Mensuelle
* **Validation par Carousel fluide** : Système de défilement fiche par fiche permettant de simuler, vérifier et sceller les salaires pour une période donnée (Mois/Année).
* **Calcul automatique des majorations** : Détection des jours fériés (`JourFerie`) avec application d'un taux double de rémunération horaire.
* **Archivage immuable** : Génération de fiches historiques (`HeureMensuelle`) scellées et protégées contre la suppression des comptes d'employés d'origine.

---

## 🛠️ Architecture Technique & Technologies

* **Framework de Présentation** : Jakarta Faces (JSF 4.0) avec injection de composants via CDI (`@Named`, `@SessionScoped`, `@RequestScoped`).
* **Couche Métier (Back-End)** : Enterprise JavaBeans (EJB 4.0 Stateless / Statefull) encapsulant les transactions et la logique de calcul.
* **Persistance & Données** : JPA 3.1 (Jakarta Persistence Unit `hsaPersistenceUnit`) mappé sur une base de données MySQL via WildFly.
* **Design UI** : Tailwind CSS (via CDN d'intégration) et Material Icons de Google.

---

## 💻 Commandes de Déploiement & Cycle de Vie

Le projet utilise Maven pour la gestion des builds et des dépendances à travers sa structure d'EAR (Enterprise Archive).

### Prérequis
1. **Java 17** (ou version supérieure) installé.
2. **Apache Maven** configuré.
3. Serveur **WildFly** démarré localement (ou variable d'environnement `JBOSS_HOME` configurée).

### ⚙️ Compilation et Déploiement
Pour compiler l'intégralité de l'application (`hsa-ejb`, `hsa-web`) et la déployer automatiquement sur le serveur WildFly en cours d'exécution :
```bash
mvn clean install wildfly:deploy