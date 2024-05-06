import React from "react";
import { useState } from "react";
import { useAuth } from "../hooks/useAuth";

export default function Login() {
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");

  const { login, error, isLoading } = useAuth();

  const onButtonLoginClick = () => {
    login(username, password);
  };

  return (
    <>
      <input
        type="text"
        name="username"
        value={username}
        placeholder="Username"
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        name="password"
        value={password}
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={onButtonLoginClick}>Login</button>
      {error && <p className="text-red-700">{error.error}</p>}
    </>
  );
}
