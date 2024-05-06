import axios from "axios";
import { useEffect, useState } from "react";

export default function Rating() {
	const [rating, setRating] = useState(null);

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

	const renderStars = () => {
		const stars = [];

		for (let i = 0; i < 5; i++) {
			if (i < rating) {
				stars.push(<span key={i}>&#9733;</span>);
			} else {
				stars.push(<span key={i}>&#9734;</span>);
			}
		}

		return stars;
	};

	return (
		<div className="flex flex-col align-middle">
			<h1>Average rating: {rating}</h1>
			<div>{rating !== null && renderStars()}</div>
		</div>
	);
}
