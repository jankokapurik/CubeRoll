// LevelSelector.jsx
import React from "react";

const LevelSelector = ({ onSelectLevel }) => {
	const levels = Array.from({ length: 10 }, (_, i) => i + 1);

	return (
		<div className="level-selector">
			<h2 className="mb-4">Choose a Level</h2>
			<div className="level-buttons">
				{levels.map((level) => (
					<button
						key={level}
						className="level-button bg-gray-200 hover:bg-gray-300 px-4 py-2 rounded-md m-2 cursor-pointer"
						onClick={() => onSelectLevel(level)}
					>
						Level {level}
					</button>
				))}
			</div>
		</div>
	);
};

export default LevelSelector;
