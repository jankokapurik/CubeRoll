require("dotenv").config;
require("./db/db");

const express = require("express");
const cors = require("cors");

const app = express();
const PORT = process.env.PORT;

app.use(express.json());
app.use(cors());

const userRoute = require("./routes/user");
app.use("/api/v1/user", userRoute);

try {
  app.listen(PORT, () => {
    console.log(PORT);
  });
} catch (error) {
  console.error(error);
}
