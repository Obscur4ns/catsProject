"use strict";

(function() {
    const baseURL = "http://localhost:8080";

    axios.get(`${baseURL}/`)
        .then(res => {
            console.log(res);
            console.log("Data: ", res.data);
        }).catch(err => console.log(err));
    })

    const updateCat = id => {
        axios.update(`${baseURL}/updateCat/${id}`)
            .then(res => {
                console.log(res);
                getAllCats();
            }).catch(err => console.log(err));
            
            const updateButton = document.createElement('button');
            updateButton.innerText = "Update";
            updateButton.addEventListener('click', () => updateCat(cat.id));
            newCat.appendChild(updateButton);
            outputDiv.appendChild(newCat);
    }

    document.querySelector('input[name=""]').value = 'Whatever you want!';