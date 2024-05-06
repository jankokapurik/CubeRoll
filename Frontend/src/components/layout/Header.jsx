import React from "react";
import { NavLink } from "react-router-dom";

const Header = () => (
	<header className="bg-gray-200 p-4">
		<div className="flex justify-between">
			<ul>
				<li>gamestudio</li>
			</ul>
			<NavLink to="">
				<h1 className="font-Cube_Font_Logo text-3xl">Cube roll</h1>
			</NavLink>

			<ul className="flex space-x-2 first:p-4	">
				<li>
					<NavLink to="/scores">Hall of fame</NavLink>
				</li>
				<li>
					<NavLink to="/comments">Commnts</NavLink>
				</li>
				<li>
					<NavLink to="/login">Login</NavLink>
				</li>
				<li>
					<NavLink to="/register">Register</NavLink>
				</li>
			</ul>
		</div>
	</header>
);

export default Header;
