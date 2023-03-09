# PlantUML Converter 1.0.0

PlantUML Converter är en tjänst för konvertering av YAML/PUML text till UML diagram i form av .svg. Inmatning av data kan ske via en post klient eller via gränssnittet.


## Endpoints

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

```
![readme-puml-image](https://user-images.githubusercontent.com/89127725/224014790-d2242426-d3b6-45d4-8d59-458d67b6055e.png)


```



---


POST -->  http://localhost:8080/api/puml/text




Created by Felix Jacobsen
