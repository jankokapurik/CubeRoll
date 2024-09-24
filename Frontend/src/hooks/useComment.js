import axios from "axios";
import { useState } from "react";

export const useComment = () => {
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const addComment = async (user, comment) => {
    setIsLoading(true);
    console.log(user.username, comment);

    try {
      const response = await axios.post("/api/comment", {
         user: user.username,
         comment,
      });

      if (response.status === 200) {
        console.log("Successfull add a comment");
      }
    } catch (error) {
      setError(error.response.data);
      setIsLoading(false);
    }
  };

  return { addComment, error, isLoading };
};
