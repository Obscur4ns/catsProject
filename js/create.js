"use strict";

const baseURL = "http://localhost:8080";

axios.get(`${baseURL}/`)
.then(res => {
    console.log(res);
    console.log("Data: ", res.data);
}).catch(err => console.log(err));

const renderCat = (cat, outputDiv) => {
    const newCat = document.createElement('div');
    
    const catName = document.createElement("h3");
    catName.innerText = cat.name;
    newCat.appendChild(catName);

    const catAge = document.createElement("p");
    catAge.innerText = `Age: ${cat.age}`;
    newCat.appendChild(catAge);

    const catBreed = document.createElement("p");
    catBreed.innerText = `Breed: ${cat.breed}`;
    newCat.appendChild(catBreed);

    const catCutie = document.createElement("p");
    catCutie.innerText = `Cutie? ${cat.cutie}`;
    newCat.appendChild(catCutie);

    const catColouring = document.createElement("p");
    catColouring.innerText = `Colouring: ${cat.colouring}`;
    newCat.appendChild(catColouring);

    outputDiv.appendChild(newCat);
}
