
<h1 align="center">
    deadlock-arena
</h1>

## feature proposals
- login-ui
- microservice to handle userData
- database creation
- spring security for password and username
- an email messaging system
- integrate extra fields in datastore
- extend bases for dto and entity
- replace reference data tables
- figure out if possible to map champion_ref to champion
   
## to-do
- message sync
- css file sync properties
- integrate javaFX with hibernate
- write test-cases
- push blobs into database
- rename schema to all capital
- setup sonarQube
- setup checkstyle
- change response in annotation to dto.class for all controllers
- add to draw.io: audacity and photoshop/paint
    
## developer workstation setup
- install eclipse enterprise Java Devleopment Tools 3.19
- install javafx on eclipse
- check terminate and relaunch when launching
- set default Java perspective
- format on save
- editor -> content assist
- Window > Preferences > Java > Editor > Save Actions , final keyword and this.
- configure lombok
- right click each folder on eclipse, Build Path -> Use as source folder
- configure user dictionary

```python
username regex:
(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$
 └─────┬────┘└───┬──┘└─────┬─────┘└─────┬─────┘ └───┬───┘
       │         │         │            │           no _ or . at the end
       │         │         │            │
       │         │         │            allowed characters
       │         │         │
       │         │         no __ or _. or ._ or .. inside
       │         │
       │         no _ or . at the beginning
       │
       username is 8-20 characters long
```
