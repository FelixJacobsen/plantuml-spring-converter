# PlantUML Converter

PlantUML Converter är en tjänst för konvertering av YAML/PUML text till UML diagram i form av .svg
Inmatning av data kan ske via en post klient eller via gränssnittet. Anropen går till 


## Endpoints
<details>
<summary>YAML</summary>

```
POST: http://localhost:8080/api/yaml/text
```

Exempel yaml request
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

Response

```
![img.png](![image](https://user-images.githubusercontent.com/89127725/223968201-7cabea39-d0ac-42d3-b05b-af3ff9548139.png)
)

![Uploading image.png…]()

```



---

<details>
<summary>PUML</summary>
POST -->  http://localhost:8080/api/puml/text
<details>



Created by Felix Jacobsen
