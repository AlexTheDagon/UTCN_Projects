const express = require('express');
const app = express();
const handlebars = require('express-handlebars');
const port = process.env.port || 3000


app.use(express.json());



// Defining routes
const tableType = require("./routes/DB/TypeDB");
const tableManufacturer = require("./routes/DB/ManufacturerDB");
const tableProducts = require("./routes/DB/ProductsDB");
const tableCustomer = require("./routes/DB/CustomerDB");
const tableStation = require("./routes/DB/StationDB");
const tableReservationInfo = require("./routes/DB/ReservationInfoDB");
const tableReservations = require("./routes/DB/ReservationsDB");

// Defining frontend routes
const products = require("./routes/products");
const home = require("./routes/home");
const search = require("./routes/search");
const addproduct = require("./routes/addproduct");
const station = require("./routes/station");

//Backend routes
app.use('/database/type', tableType);
app.use('/database/manufacturer', tableManufacturer);
app.use('/database/products', tableProducts);
app.use('/database/customer', tableCustomer);
app.use('/database/station', tableStation);
app.use('/database/reservationinfo', tableReservationInfo);
app.use('/database/reservations', tableReservations);

//Frontend Routes
app.use('/products',products);
app.use('/', home);
app.use('/products/search', search);
app.use('/addproduct', addproduct);
app.use('/station', station);

//Sets our app to use the handlebars engine
app.set('view engine', 'hbs');
//Sets handlebars configurations
app.engine('hbs', handlebars({
  layoutsDir: __dirname + '/views/layouts',
  partialsDir: __dirname + '/views/partials/',
  extname: 'hbs',
  defaultLayout: "main"
}));

app.use(express.static('public'));

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`)
});


