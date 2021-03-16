const express = require("express");
const router = express.Router();
const axios = require('../axios');

router.get("/:rID", (req, res) => {

    axios.get('/database/station/' + req.params.rID)
    .then(function (response) {
        let data = response.data;
        res.render("station",{station: data});
    })
    .catch(function (error) {
        console.log(error);
    })
});
module.exports = router;