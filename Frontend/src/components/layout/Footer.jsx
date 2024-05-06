import React from "react";
import { NavLink } from "react-router-dom";

const Footer = () => (
	<footer className="h-16">
		<div className="container mx-auto text-center">
			<hr />
			<span>
				© Jan Kapurik <br/> 
				2023 Gamestudio was developped at{" "}
				<a href="http://kpi.fei.tuke.sk">KPI FEI TUKE</a>
			</span>
		</div>
	</footer>
);

export default Footer;
