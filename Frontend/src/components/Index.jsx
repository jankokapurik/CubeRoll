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
    <main className="flex flex-col align-middle space-y-10">
      <button
        onClick={handlePlayClick}
        className="p4 mt-10 p-1 text-lg rounded-md border-2 border-black hover:text-white hover:bg-black"
      >
        play
      </button>
      <Rating/>
      <HallOfFame />
      <Comments />
    </main>
  );
}
