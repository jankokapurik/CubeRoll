import React, { useState, useEffect } from "react";
import axios from "axios";

const HallOfFame = () => {
	const [scores, setScores] = useState([]);
	

	useEffect(() => {
		const fetchScores = async () => {
			try {
				const response = await axios.get("/api/score/cuberoll");
				setScores(response.data);
			} catch (error) {
				console.error("Error fetching scores:", error);
			}
		};

		fetchScores();
	}, []);

	

	return (
		<div className="max-w-4xl mx-auto">
			<h2 className="text-2xl font-semibold mb-4">Hall of Fame</h2>
			<table className="table-auto w-full">
				<thead>
					<tr>
						<th className="px-4 py-2 bg-gray-200 text-gray-800">
							Player
						</th>
						<th className="px-4 py-2 bg-gray-200 text-gray-800">
							Points
						</th>
						<th className="px-4 py-2 bg-gray-200 text-gray-800">
							Played On
						</th>
					</tr>
				</thead>
				<tbody>
					{scores.map((score, index) => (
						<tr key={index}>
							<td className="border px-4 py-2">{score.player}</td>
							<td className="border px-4 py-2">{score.points}</td>
							<td className="border px-4 py-2">
								{new Date(score.playedOn).toLocaleString()}
							</td>
						</tr>
					))}
				</tbody>
			</table>
			
		</div>
	);
};

export default HallOfFame;
