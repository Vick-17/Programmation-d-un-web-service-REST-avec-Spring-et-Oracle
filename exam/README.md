# Projet JEE, Spring, Oracle — Web service REST (Exam)

Ce dépôt implémente une API REST conforme au sujet « Projet JEE, Spring, Oracle » pour ME Expert IT – Applications Intelligentes et Big Data (1ère année).

Objectif: exposer une API de gestion Patients / Médecins / Consultations / Prescriptions, avec authentification JWT, pagination/recherche, et gestion de documents joints aux consultations.


## Stack technique
- Java 17, Spring Framework 6, Spring Boot 3
- Spring Web, Spring Data JPA, Spring Security
- Base de données: Oracle XE (profil par défaut); MariaDB possible par simple changement de propriétés
- MapStruct pour le mapping Entity ⇄ DTO
- springdoc-openapi (Swagger UI) pour la documentation


## Architecture (couches + DTO)
- Controllers: `src/main/java/com/projectexam/exam/Controllers`
- Services: `src/main/java/com/projectexam/exam/Services`
- Repositories: `src/main/java/com/projectexam/exam/Repositories`
- Models (JPA): `src/main/java/com/projectexam/exam/Models`
- DTOs: `src/main/java/com/projectexam/exam/Dtos` et CreateDtos
- Mappers (MapStruct): `src/main/java/com/projectexam/exam/Mappers`
- Sécurité (JWT): `src/main/java/com/projectexam/exam/Security`
- Stockage fichiers: `src/main/java/com/projectexam/exam/Services/Filestorage`

Les contrôleurs n’exposent que des DTOs; la logique métier est centralisée dans les services; la persistance est encapsulée par les repositories.


## MCD → Entités JPA
MCD (Consultation, Patient, Medecin, Medicament, Prescription) traduit avec:
- `Patient(nSS PK, nomPAT, password, consultations)`
- `Medecin(matricule PK, nomMED, password, consultations)`
- `Medicament(code PK, libelle, prescriptions)`
- `Consultation(numero PK auto, date, medecin @ManyToOne, patient @ManyToOne, prescriptions @OneToMany(cascade=ALL, orphanRemoval=true), document)`
- `Prescription(id @GeneratedValue via BaseEntity, consultation @ManyToOne, medicament @ManyToOne, nbPrises)`

Relations:
- Un patient assiste 1..n consultations; un médecin donne 0..n consultations
- Une consultation prescrit 0..n médicaments via Prescription (nbPrises)

Pour éviter les boucles de sérialisation/mapping:
- `PatientMapper`/`MedecinMapper`: ignore `consultations`
- `MedicamentMapper`: ignore `prescriptions`
- `PrescriptionMapper`: ignore la référence inverse `consultation`
- `ConsultationMapper`: utilise les mappers ci‑dessus


## Sécurité (JWT)
- Authentification via `POST /login` (JSON):
  - Médecin: `{ "matricule": "<id>", "password": "<pwd>" }`
  - Patient: `{ "nss": "<id>", "password": "<pwd>" }`
- En cas de succès: en‑têtes de réponse `access_token` et `refresh_token`
- Pour les appels protégés: `Authorization: Bearer <access_token>`

Code clé:
- Filtres: `Security/CustomAuthenticationFilter`, `Security/CustomAuthorizationFilter`
- Users: `Security/DomainUserDetailsService` (cherche par NSS ou matricule)
- Config: `Security/SecurityConfig` (CORS, CSRF off, Stateless, filtres ajoutés)


## Configuration BDD
Fichier: `src/main/resources/application.properties`
- Contexte: `server.servlet.contextPath=/api`
- Oracle (défaut):
  - `spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/FREEPDB1`
  - `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect`
- MariaDB: remplacer la `url`, `driver-class-name` et le `dialect` (ex. `org.hibernate.dialect.MariaDBDialect`)

Migrations: possibilité d’ajouter Flyway/Liquibase (non inclus par défaut).


## Documentation Swagger
- UI: `http://localhost:8080/api/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/api/v3/api-docs`

Si 404, tester: `http://localhost:8080/api/swagger-ui.html` ou `.../swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config`.


## Endpoints principaux
Base path: `/api`

Authentification
- POST `/login` — récupère `access_token`/`refresh_token` dans les headers

Patients
- GET `/patient` — liste paginée (hérité du contrôleur générique)
- GET `/patient?search=...` — recherche paginée par `nomPAT` (contains, ignore case) ou NSS numérique
- POST `/patient/register` — création (NSS unique, mot de passe encodé)

Médecins
- GET `/medecin` — liste paginée (hérité du générique)
- GET `/medecin?search=...` — recherche paginée par `nomMED` ou matricule numérique
- POST `/medecin/register` — création (matricule unique, mot de passe encodé)

Consultations
- GET `/consultation` — liste paginée
- GET `/consultation/{numero}` — détail (inclut prescriptions et document si présent)
- GET `/consultation/patient/{nss}` — liste paginée des consultations d’un patient
- POST `/consultation/create` — création; médecin déduit du JWT; patient par `nSS`; prescriptions optionnelles
- PUT `/consultation/{numero}` — mise à jour (date et/ou ajout de prescriptions); refus si date passée
- DELETE `/consultation/{numero}` — suppression (cascade prescriptions)

Documents de consultation
- POST `/consultation/{numero}/document` — upload multipart (`file`); refuse si date passée; stocké via `FileStorageService`
- GET `/consultation/{numero}/document` — affichage/téléchargement (Content-Type déduit)

Prescriptions
- Via `create`/`update` de consultation. Endpoints dédiés ajout/remplacement/suppression possibles si besoin.


## Exemples de requêtes
Authentification (médecin)
```
curl -i -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"matricule":"12345","password":"mon_pwd"}'
```

Créer une consultation (avec prescriptions)
```
curl -X POST http://localhost:8080/api/consultation/create \
  -H "Authorization: Bearer <access_token>" -H "Content-Type: application/json" \
  -d '{"date":"2025-10-01","patient":{"nSS":98765},"prescriptions":[{"medicament":{"code":1},"nbPrises":2}]}'
```

Mettre à jour une consultation
```
curl -X PUT http://localhost:8080/api/consultation/123 \
  -H "Authorization: Bearer <access_token>" -H "Content-Type: application/json" \
  -d '{"prescriptions":[{"medicament":{"code":5},"nbPrises":1}]}'
```

Uploader un document
```
curl -X POST http://localhost:8080/api/consultation/123/document \
  -H "Authorization: Bearer <access_token>" \
  -F "file=@/chemin/vers/fichier.png"
```

Afficher le document
```
curl -OJ http://localhost:8080/api/consultation/123/document
```

Lister consultations d’un patient
```
curl "http://localhost:8080/api/consultation/patient/98765?page=0&size=10&sort=date,desc"
```


## Gestion des erreurs et validations
- Exceptions validées côté service (ex: consultation passée → `409 CONFLICT`; entité introuvable → `400/404`).
- Possibilité d’ajouter un `@ControllerAdvice` pour des réponses d’erreur uniformes.
- DTOs extensibles avec Bean Validation (`@NotNull`, `@Min`, etc.) et `@Valid` dans les contrôleurs.


## Stockage de fichiers
- Propriété `filestorage.path` (optionnelle). Si absente, fallback vers `./uploads` (créé au démarrage).
- Service: `FileStorageServiceImpl` (hash MD5 du fichier + extension MIME, copie avec remplacement, chargement en `Resource`).


## Tests
- Exemple de test unitaire: `PatientServiceImplTest` (Mockito, capture d’arguments, validation encodage pwd).
- Lancer: `mvn test` ou `mvn -Dtest=PatientServiceImplTest test`
- Note JDK récents: ajouter `-XX:+EnableDynamicAgentLoading` à Surefire pour supprimer les warnings ByteBuddy/Mockito.


## Exigences → Checklist
- [x] Spring v6 / Boot v3 / Web / Data JPA / Security
- [x] Connexion Oracle; MariaDB possible par propriétés
- [x] Structure en couches + DTO/MapStruct
- [x] Entités JPA conformes au MCD
- [x] Login/password → JWT (filtres custom)
- [x] Patients paginés + recherche nom/NSS
- [x] Médecins paginés + recherche nom/matricule
- [x] Consultations d’un patient (paginé)
- [x] Détail des médicaments d’une consultation (dans `ConsultationDto`)
- [x] CRUD consultations futures (update/delete refus optionnel à activer pour passé; update déjà bloqué)
- [x] Modifier la prescription (via update/ajout prescriptions)
- [x] Joindre un document à la consultation (upload + download)
- [x] Swagger/OpenAPI disponible
- [x] Logging et CORS configurés; CSRF désactivé pour API stateless
- [x] Tests Spring Boot (exemples unitaires); extensible vers WebMvc/Intégration
- [ ] Conseillé: Handler global des erreurs, profiles `oracle`/`mariadb`, migrations Flyway, SonarQube


## Démarrage
- Configurer `application.properties` (BDD, `server.servlet.contextPath=/api` par défaut)
- Démarrer: `mvn spring-boot:run`
- Swagger: `http://localhost:8080/api/swagger-ui/index.html`


## Notes
- Les endpoints protégés doivent inclure `Authorization: Bearer <access_token>` après un `POST /login`.
- Les dates de consultation passées ne sont pas modifiables (règle métier).
- Les prescriptions sont créées/supprimées en cascade avec la consultation.

