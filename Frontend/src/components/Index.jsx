import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import HallOfFame from "./HallOfFame";
import Comments from "./Comments";
import Rating from "./Rating";

export default function Index() {
  const user = localStorage.getItem("user");
  const navigate = useNavigate();
  
  const handlePlayClick = () => {
    if (user) {
      navigate("cuberoll/new");
    } else {
      navigate("login");
    }
  };
  
  return (
		<main className="w-10/12 flex flex-col items-center align-middle space-y-20">
			<button
				onClick={handlePlayClick}
				className=" p-2 px-12 mt-20 w-fit text-4xl rounded-md border-2 border-black hover:text-white hover:bg-black"
			>
				play
			</button>
			<hr className="w-full" /> 
			<Rating />
			<hr className="w-full" />
			<HallOfFame />
			<hr className="w-full" />
			<Comments />
		</main>
  );
}
