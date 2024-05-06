import React from "react";
import { NavLink } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";

export default function Header() {
  const user = localStorage.getItem("user");
  const { logout } = useAuth();

  return (
    <header className="bg-black p-4 text-white">
      <div className="flex justify-between">
        <NavLink to="/">
          <div className="font-Cube_Font_Logo text-3xl flex flex-row hover:space-x-1">
            <h1>C</h1>
            <h1>u</h1>
            <h1>b</h1>
            <h1>e </h1>
            <h1>r</h1>
            <h1>o</h1>
            <h1>l</h1>
            <h1>l</h1>
          </div>
        </NavLink>

        <ul className="flex space-x-2 first:p-4	">
          {!user && (
            <>
              <li>
                <NavLink to="/login">Login</NavLink>
              </li>
              <li>
                <NavLink to="/register">Register</NavLink>
              </li>
            </>
          )}
          {user && (
            <>
			<li>
				<NavLink to="/cuberoll/new">
					play
				</NavLink>
			</li>
              <li
                onClick={() => {
                  logout();
                }}
              >
                Logout
              </li>
            </>
          )}
        </ul>
      </div>
    </header>
  );
}
