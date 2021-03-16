const express = require("express");
const router = express.Router();
const axios = require('../axios');

router.get("/", (req, res) => {

    axios.get('/database/products/ListAllProducts')
    .then(function (response) {
        let data = response.data;
        res.render("products",{products: data});
    
    })
    .catch(function (error) {
        console.log(error);
    })
});

module.exports = router;