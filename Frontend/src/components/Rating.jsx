import React, { useEffect, useState } from "react";
import { useRating } from "../hooks/useRating";

const Rating = () => {
  const user = localStorage.getItem("user");
  const [rating, setRating] = useState(null);
  const [hoverRating, setHoverRating] = useState(null);

  const { addRating } = useRating();

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

  const renderStars = (rating) => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      stars.push(<span key={i}>{i <= rating ? "★" : "☆"}</span>);
    }
    return stars;
  };

  const addStars = () => {
    const stars = [];

    for (let i = 1; i <= 5; i++) {
      stars.push(
        <span
          key={i}
          style={{ cursor: "pointer" }}
          onClick={() => addRating(user, i)}
          onMouseEnter={() => setHoverRating(i)}
          onMouseLeave={() => setHoverRating(null)}
        >
          {i <= hoverRating ? "★" : "☆"}
        </span>
      );
    }

    return stars;
  };

  return (
    <div className="flex flex-col align-middle">
      <h1>Average rating: </h1>
      <div>{renderStars(rating)}</div>
      <h1>Add rating: </h1>
      <div>{addStars()}</div>
    </div>
  );
};

export default Rating;
