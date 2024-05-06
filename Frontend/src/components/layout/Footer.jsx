import React from "react";
import { NavLink } from "react-router-dom";

const Footer = () => (
	<footer className="bg-gray-800 text-white py-4">
		<div className="container mx-auto text-center">
			<hr />
			<span>
				© 2023 Gamestudio was developped at{" "}
				<a href="http://kpi.fei.tuke.sk">KPI FEI TUKE</a>
			</span>
		</div>
	</footer>
);

export default Footer;
