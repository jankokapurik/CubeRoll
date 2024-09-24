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
		<div className="flex flex-col items-center space-y-6 mt-20">
			<h1 className="text-3xl">Login</h1>
			<h1 className="text-xl mt-1">Username</h1>
			<input
				type="text"
				name="username"
				value={username}
				placeholder="Username"
				onChange={(e) => setUsername(e.target.value)}
				className="w-full px-4 py-2 border-b-2 bg-none border-black resize-none focus:outline-none focus:border-b-4"
			/>
			<h1 className="text-xl mt-1">Password</h1>
			<input
				type="password"
				name="password"
				value={password}
				placeholder="Password"
				onChange={(e) => setPassword(e.target.value)}
				className="w-full px-4 py-2 border-b-2 border-black resize-none focus:outline-none focus:border-b-4"
			/>
			<button
				onClick={onButtonLoginClick}
				className="p4 px-4 p-1 mt-2 text-lg rounded-md border-2 border-black hover:text-white hover:bg-black"
			>
				Login
			</button>
			{error && <p className="text-red-700">{error.error}</p>}
		</div>
  );
}
