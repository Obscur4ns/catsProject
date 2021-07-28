"use strict";

const baseURL = "http://localhost:5500";

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

document.querySelector("form#myForm").addEventListener('submit', (e) => { e.preventDefault();

console.log("This: ", this);
console.log("Breed: ", this.breed);

const form = e.target;
const data = {
    name: form.name.value,
    age: form.age.value,
    breed: form.breed.value,
    cutie: form.cutie.value,
    colouring: form.colouring.value
}

console.log("Data: ", data);

axios.post(`${baseURL}/createCat`, data)
.then((res) => {
    console.log(res);
    getAllCats();

    form.reset();
    form.name.focus();
}).catch(err => console.log(err));
});

var myModal = new bootstrap.Modal(document.getElementById('myModal'));
var myModalEl = document.getElementById('myModal')
myModalEl.addEventListener('hidden.bs.modal', function (event) {
})
