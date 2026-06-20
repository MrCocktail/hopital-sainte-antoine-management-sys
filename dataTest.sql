USE hopital_sainte_antoine;

-- On vide proprement les tables au singulier au cas où
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE HeureFournie;
TRUNCATE TABLE HeureMensuelle;
TRUNCATE TABLE Consultation;
TRUNCATE TABLE Medecin;
TRUNCATE TABLE Employe;
TRUNCATE TABLE Patient;
TRUNCATE TABLE JourFerie;
SET FOREIGN_KEY_CHECKS = 1;

-- 1. Insertion des Employés administratifs et infirmiers (Table Mère 'Employe')
INSERT INTO Employe (employe_code, nom, prenom, poste, salaire_base) VALUES 
(1, 'Chery', 'Jean-Baptiste', 'Secrétaire Médical', 250.00),
(2, 'Augustin', 'Dieufort', 'Comptable', 400.00),
(3, 'Jean-Louis', 'Marline', 'Infirmière Choc', 300.00),
(4, 'Destiné', 'Florence', 'Infirmière Anesthésiste', 350.00),
(5, 'Pierre', 'Lovenson', 'Technicien de Laboratoire', 280.00);

-- 2. Insertion des Médecins (Héritage JOINED : Insertion dans 'Employe' puis dans 'Medecin')
-- Dr. Frantz Dominique
INSERT INTO Employe (employe_code, nom, prenom, poste, salaire_base) VALUES (6, 'Dominique', 'Frantz', 'Médecin spécialiste', 600.00);
INSERT INTO Medecin (employe_code, specialite) VALUES (6, 'Pédiatrie');

-- Dre. Claudette Noël
INSERT INTO Employe (employe_code, nom, prenom, poste, salaire_base) VALUES (7, 'Noël', 'Claudette', 'Médecin spécialiste', 650.00);
INSERT INTO Medecin (employe_code, specialite) VALUES (7, 'Gynécologie-Obstétrique');

-- Dr. Stevenson Alexandre
INSERT INTO Employe (employe_code, nom, prenom, poste, salaire_base) VALUES (8, 'Alexandre', 'Stevenson', 'Médecin résident', 450.00);
INSERT INTO Medecin (employe_code, specialite) VALUES (8, 'Chirurgie Générale');


-- 3. Insertion des Patients
INSERT INTO Patient (patient_code, nom, prenom, adresse, telephone) VALUES 
(1, 'Casimir', 'Woodline', 'Avenue Poupelard, Port-au-Prince', '+509 3445-8821'),
(2, 'Clermont', 'Jude', 'Rue Chrétien, Nazon', '+509 4211-0093'),
(3, 'Saint-Fleur', 'Anise', 'Lalue, Port-au-Prince', '+509 3117-2245');


-- 4. Insertion des Jours Fériés de référence en Haïti (Exemples pour Mai/Juin)
INSERT INTO JourFerie (jour_ferie_code, date, description) VALUES 
(1, '2026-05-01', 'Fête de l''Agriculture et du Travail'),
(2, '2026-05-18', 'Fête du Drapeau et de l''Université');


-- 5. Insertion de l'historique des heures (HeureFournie)
-- On enregistre des journées de travail (Ex: de 08:00 à 16:00, ou avec heures sup de 07:00 à 18:00)
INSERT INTO HeureFournie (employe_code, date_travail, heure_debut, heure_fin) VALUES 
(3, '2026-06-01', '08:00:00', '16:00:00'), -- Marline (Infirmière)
(3, '2026-06-02', '08:00:00', '17:00:00'), 
(6, '2026-06-01', '09:00:00', '16:00:00'), -- Dr. Frantz (Médecin)
(6, '2026-06-02', '07:00:00', '19:00:00'); 


-- 6. Insertion d'une Consultation de test
INSERT INTO Consultation (patient_code, medecin_code, date_consultation, notes) VALUES 
(1, 6, '2026-06-01', 'Consultation de routine pour la petite Woodline. Paramètres stables.');