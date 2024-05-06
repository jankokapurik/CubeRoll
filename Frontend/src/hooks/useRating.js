import axios from "axios";
import { useState } from "react";

export const useRating = () => {
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const addRating = async (user, rating) => {
    setIsLoading(true);

    try {
      const response = await axios.post("/api/rating", {
        user: user.username,
        rating,
      });

      if (response.status === 200) {
        console.log("Successfull add a rating");
      }
    } catch (error) {
      setError(error.response.data);
      setIsLoading(false);
    }
  };

  return { addRating, error, isLoading };
};
