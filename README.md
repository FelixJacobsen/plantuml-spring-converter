# PlantUML Converter 1.0.0

PlantUML Converter är en tjänst för konvertering av YAML/PUML text till UML diagram i form av .svg. Inmatning av data kan ske via en post klient eller via gränssnittet.


# Endpoints

## Post
```
http://localhost:8080/api/yaml/text
```

### Exempel yaml request
```
doe: "a deer, a female deer"
ray: "a drop of golden sun"
pi: 3.14159
xmas: true
french-hens: 3
calling-birds: 
	- huey
	- dewey
	- louie
	- fred
xmas-fifth-day: 
	calling-birds: four
	french-hens: 3
	golden-rings: 5
	partridges: 
		count: 1
		location: "a pear tree"
	turtle-doves: two
```

### Response
![readme-yaml-image](https://user-images.githubusercontent.com/89127725/224015525-2cf3dded-9cf1-4c63-8ac0-9a830fb05e10.png)






---

## Post
```
http://localhost:8080/api/puml/text
```

### Exempel puml request
```
@startuml
participant Participant as Foo
actor       Actor       as Foo1
boundary    Boundary    as Foo2
control     Control     as Foo3
entity      Entity      as Foo4
database    Database    as Foo5
collections Collections as Foo6
queue       Queue       as Foo7
Foo -> Foo1 : To actor 
Foo -> Foo2 : To boundary
Foo -> Foo3 : To control
Foo -> Foo4 : To entity
Foo -> Foo5 : To database
Foo -> Foo6 : To collections
Foo -> Foo7: To queue
@enduml
```

### Response
![readme-puml-image](https://user-images.githubusercontent.com/89127725/224014790-d2242426-d3b6-45d4-8d59-458d67b6055e.png)

---
## Created by Felix Jacobsen
