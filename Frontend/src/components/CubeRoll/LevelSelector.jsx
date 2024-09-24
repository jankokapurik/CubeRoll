import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const LevelSelector = () => {
	const levels = Array.from({ length: 10 }, (_, i) => i + 1);
	const navigate = useNavigate()
	const [hoveredLevel, setHoveredLevel] = useState(null);

	return (
		<div>
			<h2 className="text-2xl mb-5">Choose a Level</h2>
			<div className="level-buttons flex flex-col space-y-5">
				{levels.map((level) => (
					<button
						key={level}
						className="p4 p-1 text-lg rounded-md border-2 border-black hover:text-white hover:bg-black"
						onMouseEnter={() => setHoveredLevel(level)}
						onMouseLeave={() => setHoveredLevel(null)}
						onClick={() => navigate(`/cuberoll/${level}`)}
					>
						{hoveredLevel === level && <span>{">"}</span>}
						Level {level}
						{hoveredLevel === level && <span>{"<"}</span>}
					</button>
				))}
			</div>
		</div>
	);
};

export default LevelSelector;
