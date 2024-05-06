import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const LevelSelector = () => {
	const levels = Array.from({ length: 10 }, (_, i) => i + 1);
	const navigate = useNavigate()

	return (
		<div className="level-selector">
			<h2>Choose a Level</h2>
			<div className="level-buttons flex flex-col">
				{levels.map((level) => (
					<button
						key={level}
						className="level-button bg-gray-200 hover:bg-gray-300 px-4 py-2 rounded-md m-2 cursor-pointer"
						onClick={() => navigate(`/cuberoll/${level}`)}
					>
						Level {level}
					</button>
				))}
			</div>
		</div>
	);
};

export default LevelSelector;
