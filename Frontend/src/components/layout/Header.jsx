import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import { useAuth } from "../../hooks/useAuth";


export default function Header() {
  const user = localStorage.getItem("user");
  const { logout } = useAuth();
  const [isScrolled, setIsScrolled] = useState(false);

  useEffect(() => {
		const handleScroll = () => {
			const scrollTop = window.scrollY;
			if (scrollTop > 0) {
				setIsScrolled(true);
			} else {
				setIsScrolled(false);
			}
		};

		window.addEventListener("scroll", handleScroll);
		return () => {
			window.removeEventListener("scroll", handleScroll);
		};
  }, []);

  return (
		<header
			className="bg-black p-4 text-white fixed w-full"
			style={{
				zIndex: 999,
				backgroundColor: isScrolled ? "black" : "transparent",
				color: isScrolled ? "white" : "black",
				transition: "background-color,color 1s ease-out",
				boxShadow: isScrolled ? "0 2px 4px rgba(0, 0, 0, 0.1)" : "none",
			}}
		>
			<div className="flex items-center justify-between">
				<NavLink to="/">
					<div className="font-Cube_Font_Logo text-3xl flex flex-row hover:space-x-1">
						<h1>C</h1>
						<h1>u</h1>
						<h1>b</h1>
						<h1>e</h1>
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
								<NavLink to="/cuberoll/new">play</NavLink>
							</li>
							<li
								onClick={() => {
									logout();
								}}
							>
								<img
									className={
										isScrolled
											? "h-6 w-6 invert transition duration-1000 ease-out"
											: "h-6 w-6 transition duration-1000 ease-out"
									}
									src="src\assets\logout.svg"
									alt="Logout"
								/>
							</li>
						</>
					)}
				</ul>
			</div>
		</header>
  );
}
