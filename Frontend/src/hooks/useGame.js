import axios from "axios";
import { useState } from "react";

export const useGame = () => {
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const saveGame = async (user, rating) => {
    setIsLoading(true);

    try {
      const response = await axios.post("/api/score", {
        user: user.username,
        rating,
      });

      if (response.status === 200) {
        console.log("Game Saved!");
      }
    } catch (error) {
      setError(error.response.data);
      setIsLoading(false);
    }
  };

  return { saveGame, error, isLoading };
};
