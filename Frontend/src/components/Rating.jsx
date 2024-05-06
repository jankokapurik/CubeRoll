import { useEffect, useState } from "react";


export default function Rating() {
	const [rating, setRating] = useState([]);

    useEffect(() => {
		const fetchRating= async () => {
			try {
				const response = await axios.get("/api/rating/cuberoll");
				setRating(response.data);
			} catch (error) {
				console.error("Error fetching scores:", error);
			}
		};

		fetchRating();
	}, []);
    
    return(
        
        <div className="flex flex-col">
            <h1>Average rating: {rating}</h1>
        </div>
    );
}