import axios from "axios";
import { useState } from "react";

export const useComment = () => {
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const addComment = async (user, comment) => {
    setIsLoading(true);
    console.log(user, comment);

    try {
      const response = await axios.post("/api/comment/cuberoll", {
        user: user.username,
        comment,
      });

      if (response.status === 200) {
        console.log(response);
      }
    } catch (error) {
      setError(error.response.data);
      setIsLoading(false);
    }
  };

  const getComment = async (username, password) => {
    setIsLoading(true);

    try {
      const response = await axios.post("/api/comment/cuberoll", {
        username,
        password,
      });

      if (response.status === 200) {
      }
    } catch (error) {
      setError(error.response.data);
      setIsLoading(false);
    }
  };

  return { addComment, getComment, error, isLoading };
};
