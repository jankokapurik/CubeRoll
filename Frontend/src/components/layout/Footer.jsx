import React from "react";
import { NavLink } from "react-router-dom";

const Footer = () => (
	<footer className="h-24">
		<div className="container mx-auto my-auto text-center">
			<hr />
			<span className="block mt-4">
				© Jan Kapurik <br/> 
				2023 Gamestudio was developped at{" "}
				<a href="http://kpi.fei.tuke.sk">KPI FEI TUKE</a>
			</span>
		</div>
	</footer>
);

export default Footer;
