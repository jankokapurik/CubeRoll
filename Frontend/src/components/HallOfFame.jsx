import React, { useState, useEffect } from "react";
import axios from "axios";
import Rating from "./Rating";

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
		<div className="w-9/12 mx-auto flex flex-col items-center">
			<h2 className="text-2xl mb-4">Hall of Fame</h2>
			<table className="table-auto w-full ">
				<thead>
					<tr className="bg-black text-white">
						<th className="px-4 py-2">Place</th>
						<th className="px-4 py-2">Player</th>
						<th className="px-4 py-2">Score</th>
						<th className="px-4 py-2">Played On</th>
					</tr>
				</thead>
				<tbody>
					{scores.map((score, index) => {
						const playedOnDate = new Date(score.playedOn);
                        const formattedDate = `${playedOnDate.getDate()}.${playedOnDate.getMonth() + 1}.${playedOnDate.getFullYear()}`;

                        return (
						<tr
							key={index}
							className="border-b border-black mx-auto"
						>
							<td className="px-4 py-2 text-center">{index}</td>
							<td className="px-4 py-2 text-center">{score.player}</td>
							<td className="px-4 py-2 text-center">{score.points}</td>
							<td className="px-4 py-2 text-center">
								{formattedDate}
							</td>
						</tr>
						);
					})}
				</tbody>
			</table>
		</div>
	);
};

export default HallOfFame;
