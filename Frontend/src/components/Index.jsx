import React from "react";
import { NavLink } from "react-router-dom";
import HallOfFame from "./HallOfFame";
import Comments from "./Comments";

const Index = () => (
	<main className="flex flex-col align-middle space-y-10">
       <button className="p4 mt-10 p-1 text-lg rounded-md border-2 border-black hover:text-white hover:bg-black">
         play
        </button>
        <HallOfFame/>
        <Comments/>
    </main>
);

export default Index;
