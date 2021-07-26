"use strict";

(function() {
    const baseURL = "http://localhost:8080";

    axios.get(`${baseURL}/`)
        .then(res => {
            console.log(res);
            console.log("Data: ", res.data);
        }).catch(err => console.log(err));
    })

    const deleteCat = id => {
        axios.delete(`${baseURL}/deleteCat/${id}`)
            .then(res => {
                console.log(res);
                getAllCats();
            }).catch(err => console.log(err));
            
            const deleteButton = document.createElement('button');
            deleteDutton.innerText = "Delete";
            deleteButton.addEventListener('click', () => deleteCat(cat.id));
            newCat.appendChild(deleteButton);
            outputDiv.appendChild(newCat);
    }


