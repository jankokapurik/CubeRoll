import React from "react";
import { NavLink } from "react-router-dom";

const Index = () => (
	<main>
        Our favourite games: <br/>
        <ol>
            <li><NavLink to="/cuberoll/new">Cube roll</NavLink></li>
            <li><NavLink to="">Other game</NavLink></li>
        </ol>
    </main>
);

export default Index;
