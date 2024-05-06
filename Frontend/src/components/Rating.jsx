import React, { useEffect, useState } from "react";
import axios from "axios";

const Rating = () => {
	const [rating, setRating] = useState(null);
	const [hoverRating, setHoverRating] = useState(null);

	useEffect(() => {
		const fetchRating = async () => {
			try {
				const response = await axios.get("/api/rating/cuberoll");
				setRating(response.data);
			} catch (error) {
				console.error("Error fetching scores:", error);
			}
		};

		fetchRating();
	}, []);

	const handleStarClick = async (value) => {
		try {
			// Make a POST request to submit the rating
			await axios.post("/api/rating/cuberoll", { rating: value });
			setRating(value);
		} catch (error) {
			console.error("Error submitting rating:", error);
		}
	};

	const renderStars = () => {
		const stars = [];

		for (let i = 1; i <= 5; i++) {
			stars.push(
				<span
					key={i}
					style={{ cursor: "pointer" }}
					onClick={() => handleStarClick(i)}
					onMouseEnter={() => setHoverRating(i)}
					onMouseLeave={() => setHoverRating(null)}
				>
					{i <= (hoverRating || rating) ? "★" : "☆"}
				</span>
			);
		}

		return stars;
	};

	return (
		<div className="flex flex-col align-middle">
			<h1>Average rating: </h1>
			<div>{rating !== null && renderStars()}</div>
			<h1>Rate this game</h1>
			<div>{rating !== null && renderStars()}</div>
		</div>
	);
};

export default Rating;
