const { request } = require("express");
const express = require("express");
const router = express.Router();
const axios = require('../axios');

router.get("/:pname", (req, res) => {

    axios.get('/database/products/' + req.params.pname)
    .then(function (response) {
        let data = response.data;
        res.render("products",{products: data});
    
    })
    .catch(function (error) {
        console.log(error);
    })
});
module.exports = router;