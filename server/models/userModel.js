const mongoose = require("mongoose");
const validator = require("validator");

const Schema = mongoose.Schema;
const userSchema = new Schema({
  username: { type: String, unique: true, required: true },
  password: { type: String, required: true },
});

userSchema.statics.signup = async function (username, password) {
  if (!username || !password) {
    throw Error("All fields must be filled");
  }

  if (!validator.isStrongPassword(password)) {
    throw Error("Password not strong enough");
  }
  usernameExist = await this.findOne({ username });

  if (usernameExist) {
    throw Error("Username already exist");
  }

  const user = await this.create({
    username: username,
    password: password,
  });

  return user;
};

userSchema.statics.login = async function (username, password) {
  if (!username || !password) {
    throw Error("All fields must be filled");
  }

  const user = await this.findOne({username});

  if (!user) {
    throw Error("Incorrect username");
  }

  if (password != user.password) {
    throw Error("Inccorect password");
  }

  return user;
};

module.exports = mongoose.model("User", userSchema);
