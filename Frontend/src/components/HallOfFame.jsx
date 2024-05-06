import React, { useState, useEffect } from "react";
import axios from "axios";

const HallOfFame = () => {
	const [scores, setScores] = useState([]);
	const [comment, setComment] = useState("");

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

	const handleCommentChange = (event) => {
		setComment(event.target.value);
	};

	const handleSubmitComment = async (event) => {
		event.preventDefault();
		try {
			await axios.post("/api/comment", { content: comment });
			setComment("");
		} catch (error) {
			console.error("Error submitting comment:", error);
		}
	};

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
			<form onSubmit={handleSubmitComment} className="mt-8">
				<label
					htmlFor="comment"
					className="block mb-2 font-medium text-gray-800"
				>
					Add a Comment:
				</label>
				<textarea
					id="comment"
					name="comment"
					value={comment}
					onChange={handleCommentChange}
					rows="4"
					className="w-full px-4 py-2 border rounded-md resize-none focus:outline-none focus:ring-2 focus:ring-blue-500"
				></textarea>
				<button
					type="submit"
					className="mt-4 px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
				>
					Submit Comment
				</button>
			</form>
		</div>
	);
};

export default HallOfFame;
