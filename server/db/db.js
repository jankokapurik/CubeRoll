const mongoose = require("mongoose");
require("dotenv").config();

const uri = process.env.DB_URI;

mongoose.connect(uri, { useNewUrlParser: true });
const db = mongoose.connection;

db.on("error", (error) => console.error(error));
db.once("open", () => console.log("Connected to mongo"));
