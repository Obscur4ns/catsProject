"use strict";

const baseURL = "http://localhost:8080";

axios.get(`${baseURL}/`)
    .then(res => {
        console.log(res);
        console.log("Data: ", res.data);
}).catch(err => console.log(err));

const getAllOutput = document.querySelector("#getAllOutput");
const getByIdOutput = document.querySelector("#getByIdOutput");

const catId = document.querySelector("#catId");

const getAllCats = () => {
    axios.get(`${baseURL}/getAllCats`)
    .then(res => {
        const cats = res.data;

        getAllOutput.innerHTML = "";

        cats.forEach(cat => renderCat(cat, getAllOutput));
    }).catch(err => console.log(err));
}

const getCatById = () => {
    axios.get(`${baseURL}/getCat/${catId.value}`)
    .then(res => {
        const cat = res.data;
        getByIdOutput.innerHTML = "";
        renderCat(cat, getByIdOutput);
    }).catch(err => console.log(err));
}

document.querySelector("section#getById > button").addEventListener('click', getCatById);