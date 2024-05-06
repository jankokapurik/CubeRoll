const express = require("express");
const User = require("../models/userModel");
const jwt = require("jsonwebtoken");
const router = express.Router();

const createToken = (_id) => {
  return jwt.sign({ _id }, process.env.SECRET, { expiresIn: "1d" });
};

router.post("/login", async (req, res) => {
  const { username, password } = req.body;

  try {
    const user = await User.login(username, password);

    const token = createToken(user._id);

    res.status(200).json({ user, token });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

router.post("/register", async (req, res) => {
  const { username, password } = req.body;
  try {
    const user = await User.signup(username, password);

    const token = createToken(user._id);

    res.status(200).json({ user, token });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
});

module.exports = router;
