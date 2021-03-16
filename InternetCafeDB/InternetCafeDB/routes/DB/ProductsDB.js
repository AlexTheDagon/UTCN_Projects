const express = require('express');
const router = express.Router();
const db = require('../../db');
//const MDB = require('../DB/ManufacturerDB');



// MDB.AddManufacturer('Riot', (err, result) => {
//     if(err){
//         res.status(500).send(err.sqlMessage);
//         throw err;
//     }
//     res.status(200).send(result);
// });

// List all the products from the catalog ORDERED by NAME
router.get('/ListAllProducts', (req, res) => {
    db.query('SELECT products.id, products.name as pname, m.name as mname, t.name as tname, products.buy_price, products.sell_price, products.quantity FROM products JOIN manufacturer m on products.manufacturer_id = m.id JOIN type t on products.type_id = t.id ORDER by pname', (err, result) => {
        if(err){
            res.status(500).send(err.sqlMessage);
            throw err;
        }
        res.status(200).send(result);
    });
});


///To get the product by [name]
router.get('/:pname', (req, res) => {
    let pname = '%' + req.params.pname + '%'
    db.query('SELECT products.id, products.name as pname, m.name as mname, t.name as tname, products.buy_price, products.sell_price, products.quantity FROM products JOIN manufacturer m on products.manufacturer_id = m.id JOIN type t on products.type_id = t.id WHERE products.name LIKE ?', 
    [pname], (err, result) => {
        if(err){
            res.status(500).send(err.sqlMessage);
            throw err;
        }
        res.status(200).send(result);
    });
});

///Insert the new product into the DATABASE
router.put('/AddProduct', (req, res) => {
    db.query('SELECT products.id, products.name as pname, m.name as mname, t.name as tname, products.buy_price, products.sell_price, products.quantity FROM products JOIN manufacturer m on products.manufacturer_id = m.id JOIN type t on products.type_id = t.id WHERE products.name = ? AND t.name = ? AND m.name = ?', 
    [req.body.name, req.body.type, req.body.manufacturer], (err, result) => {
        if(err){ // Second condition is in case of duplicates
            return res.status(500).send(err.sqlMessage);
            throw err;
        }
        if(typeof result[0] !== 'undefined'){
            return res.status(500).send(result);
        }
        //Now we check if the Manufacturer is already in the DATABASE
        db.query('SELECT id FROM type WHERE type.name = ?', [req.body.type], (err, resultTypeCheck) =>{
            if(err){
                return res.status(500).send(err.sqlMessage);
            }
            if(typeof resultTypeCheck[0] === 'undefined' || resultTypeCheck.lenght <= 0){
                return res.status(500).send("Bad type input");
            }
            let tID = resultTypeCheck[0].id;
            db.query("SELECT id FROM manufacturer WHERE manufacturer.name = ?;", [req.body.manufacturer],(err, resultManufacturerCheck) =>{
                if(err){
                res.status(500).send(err.sqlMessage);
                throw err;
                }
                if (typeof resultManufacturerCheck[0] === 'undefined' || resultManufacturerCheck.length <= 0) {
                    // the array is defined and has at least one element
                    db.query('INSERT INTO manufacturer (name) VALUES (?);',[req.body.manufacturer],(err, resultManufacturerInsert) => {
                        if(err){
                            return res.status(500).send(err.sqlMessage);
                        }
                        //console.log("manufacturer not into the DATABASE");//Check
                        //console.log(resultManufacturerInsert);
                        let mID = resultManufacturerInsert.insertId;
                        db.query('INSERT INTO products (name, manufacturer_id, type_id, buy_price, sell_price) VALUES(?, ?, ?, ?, ?)', 
                        [req.body.name, mID, tID, req.body.buy_price, req.body.sell_price], (err, resultAddProduct) => {
                            if(err){
                                return res.status(500).send(err.sqlMessage);
                            }
                            res.status(200).send(resultAddProduct);
                        });
                    });
                }
                else {
                    let mID = resultManufacturerCheck[0].id;
                    db.query('INSERT INTO products (name, manufacturer_id, type_id, buy_price, sell_price) VALUES(?, ?, ?, ?, ?)', 
                    [req.body.name, mID, tID, req.body.buy_price, req.body.sell_price], (err, resultAddProduct) => {
                        if(err){
                            return res.status(500).send(err.sqlMessage);
                        }
                        res.status(200).send(resultAddProduct);
                    });
                }
            });
        });
        
        /// To insert prodect into the table we need 
    });
});

// Increase the quantity by the given ammount
router.put('/UpdateQuantity', (req, res) => {
    db.query('SELECT products.id, products.name as pname, m.name as mname, t.name as tname, products.buy_price, products.sell_price, products.quantity FROM products JOIN manufacturer m on products.manufacturer_id = m.id JOIN type t on products.type_id = t.id WHERE products.name = ? AND t.name = ? AND m.name = ?', 
    [req.body.name, req.body.type, req.body.manufacturer], (err, result) => {
        if(err){
            return res.status(500).send(err.sqlMessage);
        }
        if(typeof result[0] === 'undefined'){
            return res.status(500).send("Product not found!");
        }
        let pID = result[0].id;
        db.query('UPDATE products SET quantity = quantity + ? WHERE id = ?',[req.body.quantity, pID], (err, resultUpdate) => {
            if(err){
                return res.status(500).send(err.sqlMessage);
            }
            res.status(200).send(resultUpdate);
        });
    });
});

router.put('/DeleteProduct', (req, res) => {
    db.query('SELECT products.id, products.name as pname, m.name as mname, t.name as tname, products.buy_price, products.sell_price, products.quantity FROM products JOIN manufacturer m on products.manufacturer_id = m.id JOIN type t on products.type_id = t.id WHERE products.name = ? AND t.name = ? AND m.name = ?', 
    [req.body.name, req.body.type, req.body.manufacturer], (err, result) => {
        if(err){ // Second condition is in case of duplicates
            return res.status(500).send(err.sqlMessage);
        }
        if(typeof result[0] === 'undefined'){
            return res.status(500).send(result);
        }
        let delID = result[0].id;
        console.log(delID);
        //Now we check if the Manufacturer is already in the DATABASE
        db.query('DELETE FROM products WHERE id = ?', [delID], (err, resultDel) =>{
            if(err){
                return res.status(500).send(err.sqlMessage);
            }
            res.status(200).send(resultDel);
        });
        
        /// To insert prodect into the table we need 
    });
});



module.exports = router;