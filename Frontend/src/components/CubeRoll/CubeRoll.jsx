// CubeRoll.jsx
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { fetchField } from "./_api/gameFieldService"; // Import the fetchField function

const CubeRoll = () => {
	let { level } = useParams();
	const [data, setData] = useState(null);

	useEffect(() => {
		const handleFetchData = async () => {
			try {
				const data = await fetchField(level); // Use the fetchField function to fetch data
				setData(data);
			} catch (error) {
				// Handle the error here
				console.error("Error fetching field:", error);
			}
		};
		handleFetchData();
	}, [level]); // Include level in the dependency array to re-fetch data when level changes

	return (
		<div className="cube-roll">
			<h2>Game for Level {level}</h2>
			<p>{data}</p>
		</div>
	);
};

export default CubeRoll;
